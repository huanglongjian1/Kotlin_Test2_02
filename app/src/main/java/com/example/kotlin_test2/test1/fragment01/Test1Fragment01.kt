package com.example.kotlin_test2.test1.fragment01

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.kotlin_test2.R
import com.example.kotlin_test2.base.kotlin_base.BaseFragmentKT
import com.example.kotlin_test2.databinding.FragmentTest1Fragment01Binding
import com.example.kotlin_test2.test1.fragment01.viewpage2.BlankFragment
import com.example.kotlin_test2.test1.fragment01.viewpage2.BlankFragment2
import com.example.kotlin_test2.test1.fragment01.viewpage2.BlankFragment3
import com.example.kotlin_test2.test1.fragment01.viewpage2.Viewpage2FragmentAdapter

class Test1Fragment01 : BaseFragmentKT<Test1Fragment01ViewModel, FragmentTest1Fragment01Binding>() {

    companion object {
        fun newInstance() = Test1Fragment01()
    }

    override fun createViewModel(): Class<Test1Fragment01ViewModel> {
        return Test1Fragment01ViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_test1_fragment01
    }

    override fun initView() {
        binding.test1Fragment01Tv.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_test1Fragment01_to_test1Fragment02)
        }
        val fragments= mutableListOf<Fragment>()
        fragments.add(BlankFragment.newInstance("参数一","参数二"))
        fragments.add(BlankFragment2.newInstance("参数一","参数二"))
        fragments.add(BlankFragment3.newInstance("参数一","参数二"))
        val viewpage2Adapter = Viewpage2FragmentAdapter(requireActivity(), fragments = fragments)
        binding.test1Fragment01Viewpage2.adapter = viewpage2Adapter

        fragments.add(BlankFragment.newInstance("参数2","参数3"))

    }

    override fun initData() {

    }


}