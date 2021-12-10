package com.app.marvelapp

import com.app.marvelapp.common.result.MyResult
import com.app.marvelapp.feature.detail.domain.GetCharacterDetailUseCase
import com.app.marvelapp.feature.detail.ui.DetailCharacterViewModel
import com.app.marvelapp.feature.listofcharacters.domain.MarvelCharacter
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test


class DetailCharacterViewModelTest: BaseUnitTest() {


    private val getCharacterDetailUseCase: GetCharacterDetailUseCase = mockk(relaxed = true)
    private lateinit var viewModel: DetailCharacterViewModel

    @Before
    fun initViewModel() {
        viewModel = DetailCharacterViewModel(
            detailCharacterId = 1,
            getCharacterDetailUseCase = getCharacterDetailUseCase
        )
    }

    @Test
    fun `test that when getCharacterDetailUseCase response with an empty description the value of the flow must be true`() =
        runBlockingTest {
            coEvery { getCharacterDetailUseCase.invoke(1L) } coAnswers {
                MyResult.Ok(
                    MarvelCharacter(description = "")
                )
            }
            viewModel.getDetail()
            assert(viewModel._displayNoDescription.value)
        }

    @Test
    fun `test that when getCharacterDetailUseCase response with a name and a description the value of the flow must be this name and description`() =
        runBlockingTest {
            coEvery { getCharacterDetailUseCase.invoke(1L) } coAnswers {
                MyResult.Ok(
                    MarvelCharacter(name = "name", description = "description")
                )
            }
            viewModel.getDetail()
            assert(viewModel._characterDetailInfo.value.description == "description")
            assert(viewModel._characterDetailInfo.value.name == "name")
        }


}