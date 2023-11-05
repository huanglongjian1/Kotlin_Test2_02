package com.example.kotlin_test2.test1.fragment02

import androidx.lifecycle.lifecycleScope
import com.example.kotlin_test2.R
import com.example.kotlin_test2.api.ApiService
import com.example.kotlin_test2.api.tianapi.bean.loadHttp
import com.example.kotlin_test2.base.kotlin_base.BaseFragmentKT
import com.example.kotlin_test2.base.retrofit.RetrofitBuilder
import com.example.kotlin_test2.databinding.FragmentTest1Fragment02Binding
import com.example.kotlin_test2.util.Loge
import com.example.mvvm.common.ExceptionUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Test1fragment02 : BaseFragmentKT<Test1Fragment02ViewModel, FragmentTest1Fragment02Binding>() {

    companion object {
        fun newInstance() = Test1fragment02()
    }



    override fun getLayoutId(): Int {
        return R.layout.fragment_test1_fragment02
    }

    override fun initView() {
        binding.test1Fragment02Tv.setOnClickListener {
            val map = mutableMapOf<String, String>()
            map["key"] = ApiService.KEY_TIANAPI

            lifecycleScope.loadHttp(
                start = {
                    Loge.e("开始渣")
                },
                request = {
                    RetrofitBuilder.create<ApiService>(ApiService.BASEURL_TIANAPI).getBadBoyMSG(map)
                },
                error = { code, msg ->
                    Loge.e(code + ":" + msg)
                },
                response = {
                    Loge.e(it.content)

                },

                end = {
                    Loge.e("渣完了")
                }
            )


        }
    }

    override fun initData() {

    }


}