package com.app.marvelapp

import com.app.marvelapp.common.navigation.Navigator
import com.app.marvelapp.common.result.MyResult
import com.app.marvelapp.feature.favorites.domain.DeleteFavoriteUseCase
import com.app.marvelapp.feature.favorites.domain.GetFavoritesUseCase
import com.app.marvelapp.feature.listofcharacters.domain.GetCharactersUseCase
import com.app.marvelapp.feature.listofcharacters.domain.MarvelCharacter
import com.app.marvelapp.feature.listofcharacters.domain.SaveFavoriteUseCase
import com.app.marvelapp.feature.listofcharacters.ui.ListOfCharactersViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class ListOfCharactersTest: BaseUnitTest() {

    private val getCharactersUseCase: GetCharactersUseCase = mockk(relaxed = true)

    private val getFavoriteUseCase: GetFavoritesUseCase = mockk(relaxed = true)
    private val saveFavoritesUseCase: SaveFavoriteUseCase = mockk(relaxed = true)
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase = mockk(relaxed = true)
    private val navigator: Navigator = mockk(relaxed = true)

    private lateinit var listOfCharactersViewModel: ListOfCharactersViewModel

    @Before
    fun initViewModel() {
        listOfCharactersViewModel = ListOfCharactersViewModel(
            getCharactersUseCase,
            getFavoriteUseCase,
            saveFavoritesUseCase,
            deleteFavoriteUseCase,
            navigator,
        )
    }

    @Test
    fun `test that when getCharacterUseCase response with one Marvel Character the expected list size is one`() =
        runBlockingTest {
            coEvery { getCharactersUseCase.invoke(Unit) } coAnswers {
                MyResult.Ok(
                    listOf(
                        MarvelCharacter(1, name = "Agent Zero")
                    )
                )
            }
            listOfCharactersViewModel.getCharacters()
            assert(listOfCharactersViewModel.getListOfCharacters().value.size == 1)
            assert(!listOfCharactersViewModel._shouldDisplayError.value)

        }

    @Test
    fun `test that when getCharacterUseCase response with concrete error the message is the expected and visibility of the component is true`() =
    runBlockingTest {
        coEvery { getCharactersUseCase.invoke(Unit) } coAnswers {
            MyResult.Err(
                R.string.api_error_no_internet
            )
        }
        listOfCharactersViewModel.getCharacters()
        assert( listOfCharactersViewModel._displayError.value == R.string.api_error_no_internet)
        assert(listOfCharactersViewModel._shouldDisplayError.value)
    }



}