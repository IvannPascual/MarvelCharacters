package com.app.marvelapp.feature.listofcharacters.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.app.marvelapp.R
import com.app.marvelapp.base.ui.BaseFragment
import com.app.marvelapp.common.observe
import com.app.marvelapp.databinding.FragmentListOfCharactersBinding
import com.app.marvelapp.feature.listofcharacters.domain.MarvelCharacter
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListOfCharactersFragment :
    BaseFragment<FragmentListOfCharactersBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_list_of_characters
    override val viewModel: ListOfCharactersViewModel by viewModel()

    override fun setupBinding(binding: FragmentListOfCharactersBinding) {
        binding.viewModel = viewModel
        binding.favButton.setOnClickListener { viewModel.loadFavorites() }
        bindToVM(binding)
    }

    private fun bindToVM(binding: FragmentListOfCharactersBinding) {
        val adapter = MarvelAdapter(::onListItemClick, ::onFavoriteClicked)
        val layoutManager = GridLayoutManager(context, 2)
        with(binding.listCharactersRecycler) {
            this.layoutManager = layoutManager
            this.adapter = adapter
            subscribeToCharactersList(adapter)
        }
    }

    private fun subscribeToCharactersList(adapter: MarvelAdapter) {
        observe(viewModel.listToDisplay) {
            it.let(adapter::submitList)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.handleOnBackPressed()
        viewModel.getCharacters()
    }

    private fun onListItemClick(item: MarvelCharacter) {
        viewModel.openDetail(item.id)
    }

    private fun onFavoriteClicked(item: MarvelCharacter) {
        viewModel.onFavoriteClicked(item)
    }

    override fun onBackPressed(): Boolean {
       viewModel.navBack()
        return false
    }

}