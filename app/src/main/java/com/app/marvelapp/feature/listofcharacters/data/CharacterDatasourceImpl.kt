package com.app.marvelapp.feature.listofcharacters.data

import com.app.marvelapp.base.ui.toApiMarvelUrl
import com.app.marvelapp.common.data.remote.*
import com.app.marvelapp.common.result.MyResult
import com.app.marvelapp.feature.listofcharacters.data.models.CharacterResultResponse
import com.app.marvelapp.feature.listofcharacters.domain.MarvelCharacter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterDatasourceImpl(
    private val api: MarvelApi,
    private val backDispatchers: CoroutineDispatcher = Dispatchers.IO,
) : CharactersDataSource {
    override suspend fun getCharacters(): MyResult<List<MarvelCharacter>,
        CompleteApiError<MarvelApiErrors>> = withContext(backDispatchers) {
        return@withContext try {
            api.getMarvelCharacters(limit = 90).getMarvelCharactersResponse()
        } catch (e: java.lang.Exception) {
            ApiErrorHandling.handle(e)
        }
    }

    override suspend fun getCharacterDetail(characterId: Long):
        MyResult<MarvelCharacter, CompleteApiError<MarvelApiErrors>>  = withContext(backDispatchers) {
        return@withContext try {
            api.getMarvelCharacterInfo(characterId).getMarvelCharacterDetailResponse()
        } catch (e: java.lang.Exception) {
            ApiErrorHandling.handle(e)
        }
    }

    private fun CharacterResultResponse.getMarvelCharactersResponse(): MyResult<List<MarvelCharacter>,
        CompleteApiError<MarvelApiErrors>> {
        return if (code == 200) {
            MyResult.Ok(toDomain())
        } else {
            MyResult.Err(code.toCompleteApiError())
        }
    }

   private fun CharacterResultResponse.getMarvelCharacterDetailResponse():MyResult<MarvelCharacter,
       CompleteApiError<MarvelApiErrors>> {
        return if (code == 200) {
           MyResult.Ok(toDetailDomain())
        } else {
            MyResult.Err(code.toCompleteApiError())
        }
    }

    private fun CharacterResultResponse.toDomain(): List<MarvelCharacter> {
        return this.dataResponse.results.map {
            MarvelCharacter(
                id = it.id,
                description = it.description,
                name = it.name,
                resourceUrl = it.thumbnailResponse.path.toApiMarvelUrl(it.thumbnailResponse.extension)
            )
        }
    }

    private fun CharacterResultResponse.toDetailDomain(): MarvelCharacter =
        dataResponse.results.firstOrNull()?.let {
            MarvelCharacter(
                id = it.id,
                description = it.description,
                name = it.name,
                resourceUrl = it.thumbnailResponse.path.toApiMarvelUrl(it.thumbnailResponse.extension)
            )
        } ?: MarvelCharacter()


    private fun Int.toCompleteApiError(): CompleteApiError<MarvelApiErrors> =
        when (this) {
            GenericMarvelApiError -> ExpectedApiError(MarvelApiErrors.GenericMarvelApiError)
            ServerError -> ExpectedApiError(ServerApiError)
            else -> UnknownApiError
        }
}

