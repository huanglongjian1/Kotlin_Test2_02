package com.example.kotlin_test2.test1.fragment03

import com.example.kotlin_test2.R
import com.example.kotlin_test2.base.kotlin_base.BaseFragmentKT
import com.example.kotlin_test2.databinding.FragmentTest1Fragment03Binding

class Test1Fragment03:BaseFragmentKT<Test1Fragment03ViewModel,FragmentTest1Fragment03Binding>() {
    override fun createViewModel(): Class<Test1Fragment03ViewModel> {
      return Test1Fragment03ViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_test1_fragment03
    }

    override fun initView() {

    }

    override fun initData() {

    }
}