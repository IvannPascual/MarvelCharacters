package com.app.marvelapp.feature.detail.ui

import androidx.navigation.fragment.navArgs
import com.app.marvelapp.R
import com.app.marvelapp.base.ui.BaseFragment
import com.app.marvelapp.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DetailCharacterFragment:  BaseFragment<FragmentDetailBinding>() {

    override val layoutRes: Int
        get() =  R.layout.fragment_detail
    private val args: DetailCharacterFragmentArgs by navArgs()

    override val viewModel: DetailCharacterViewModel by viewModel { parametersOf(args.id)}

    override fun setupBinding(binding: FragmentDetailBinding) {
        binding.viewModel = viewModel
        viewModel.getDetail()
    }

}