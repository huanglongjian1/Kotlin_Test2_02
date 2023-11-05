package com.example.kotlin_test2.test1.fragment05

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlin_test2.api.ApiService
import com.example.kotlin_test2.api.tianapi.badboyresultbean.BadBoyResultBean
import com.example.kotlin_test2.api.tianapi.bean.ResultData
import com.example.kotlin_test2.api.tianapi.bean.http
import com.example.kotlin_test2.api.tianapi.bean.launchhttp
import com.example.kotlin_test2.api.tianapi.bean.loadHttp
import com.example.kotlin_test2.base.kotlin_base.BaseViewModelKT
import com.example.kotlin_test2.base.retrofit.RetrofitBuilder
import com.example.kotlin_test2.util.Loge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class Test1Fragment05ViewModel(application: Application) : BaseViewModelKT(application) {
    fun getBaidu(): Flow<String> = callbackFlow {
        launchhttp(
            block = {
                val html =
                    RetrofitBuilder.create<ApiService>(ApiService.BASEURL_BAIDU).getBaidu().string()
                trySend(html)
            },
            onError = { e ->
                e.message?.let {
                    trySend(it)
                }
            },
            onComplete = {
                trySend("访问完成")
            }
        )
        awaitClose {
            Loge.e("关闭发射数据")
        }
    }

    fun getBadBoyResultBean(): Flow<String> = callbackFlow {
        viewModelScope.loadHttp(
            start = {},
            request = {
                val map = mutableMapOf<String, String>()
                map["key"] = ApiService.KEY_TIANAPI
                RetrofitBuilder.create<ApiService>(ApiService.BASEURL_TIANAPI).getBadBoyMSG(map)
            },
            response = {
                trySend(it.content)
            },
            error = { code, msg ->
                Loge.e(code + ":" + msg)
            },
            end = {
                Loge.e("访问完成")
            }
        )
        awaitClose {
            Loge.e("关闭发射数据")
        }
    }

    fun getBadBoyResultBean02(): LiveData<ResultData<BadBoyResultBean>> = http {

        val map = mutableMapOf<String, String>()
        map["key"] = ApiService.KEY_TIANAPI
        RetrofitBuilder.create<ApiService>(ApiService.BASEURL_TIANAPI).getBadBoyMSG(map)

    }
}