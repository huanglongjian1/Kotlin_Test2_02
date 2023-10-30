package com.example.kotlin_test2.base.kotlin_base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_test2.base.BaseViewModel
import com.example.kotlin_test2.base.fragment_base.BaseFragment

abstract class BaseFragmentKT<VM : BaseViewModel,T : ViewDataBinding?> : BaseFragment<T>() {

    protected val mViewModel by lazy {
        ViewModelProvider(requireActivity())[createViewModel()]
    }

    abstract fun createViewModel(): Class<VM>
}