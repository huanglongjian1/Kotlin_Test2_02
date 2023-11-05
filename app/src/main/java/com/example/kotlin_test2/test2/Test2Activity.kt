package com.example.kotlin_test2.test2

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.kotlin_test2.R
import com.example.kotlin_test2.base.kotlin_base.BaseActivityKT
import com.example.kotlin_test2.databinding.Test1ActivityBinding
import com.example.kotlin_test2.util.Constants
import com.example.kotlin_test2.util.Loge

@Route(path = Constants.PATH_TEST2_ACTIVITY)
class Test2Activity : BaseActivityKT<Test2ActivityViewModel, Test1ActivityBinding>() {
    override fun getContentViewId(): Int {
        return R.layout.test1_activity
    }

    override fun initData() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.test1Tv.setOnClickListener {
            Loge.e("今晚吃鸡鸡")
            Loge.e(mViewModel.hashCode().toString())
            val VM = ViewModelProvider(this)[Test2ActivityViewModel::class.java]

            Loge.e((mViewModel === VM).toString())
        }
    }
}