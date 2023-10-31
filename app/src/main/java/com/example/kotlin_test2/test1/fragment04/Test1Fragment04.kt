package com.example.kotlin_test2.test1.fragment04

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_test2.R
import com.example.kotlin_test2.util.Loge

class Test1Fragment04 : Fragment() {

    companion object {
        fun newInstance() = Test1Fragment04()
    }

    private lateinit var viewModel: Test1Fragment04ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Loge.e("合并04")
        return inflater.inflate(R.layout.fragment_test1_fragment04, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Test1Fragment04ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}