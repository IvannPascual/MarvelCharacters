package com.app.marvelapp.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.app.marvelapp.common.observe

abstract class BaseFragment<Binding : ViewDataBinding> : Fragment() {

    abstract val layoutRes: Int
    protected abstract val viewModel: BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding =
            DataBindingUtil.inflate<Binding>(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        setupBinding(binding)
        return binding.root
    }

    protected fun handleOnBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        })
    }

    open fun setupBinding(binding: Binding) {}
    open fun isFinishing(): Boolean = activity?.isFinishing ?: true
    open fun onBackPressed(): Boolean = false

}

