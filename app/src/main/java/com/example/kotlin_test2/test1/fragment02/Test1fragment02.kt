package com.example.kotlin_test2.test1.fragment02

import com.example.kotlin_test2.R
import com.example.kotlin_test2.base.kotlin_base.BaseFragmentKT
import com.example.kotlin_test2.databinding.FragmentTest1Fragment02Binding

class Test1fragment02 : BaseFragmentKT<Test1Fragment02ViewModel,FragmentTest1Fragment02Binding>() {

    companion object {
        fun newInstance() = Test1fragment02()
    }

    override fun createViewModel(): Class<Test1Fragment02ViewModel> {
    return Test1Fragment02ViewModel::class.java
    }

    override fun getLayoutId(): Int {
     return R.layout.fragment_test1_fragment02
    }

    override fun initView() {

    }

    override fun initData() {

    }


}