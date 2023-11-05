package com.example.kotlin_test2.base.kotlin_base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_test2.base.BaseViewModel
import com.example.kotlin_test2.base.fragment_base.BaseFragment
import java.lang.reflect.ParameterizedType

abstract class BaseFragmentKT<VM : BaseViewModel, T : ViewDataBinding?> : BaseFragment<T>() {

    protected val mViewModel by lazy {
        ViewModelProvider(requireActivity())[createViewModel()]
    }

    private fun createViewModel(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return type as Class<VM>
    }
}