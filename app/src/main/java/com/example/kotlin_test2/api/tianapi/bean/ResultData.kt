package com.example.kotlin_test2.api.tianapi.bean

import android.text.TextUtils
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.kotlin_test2.base.kotlin_base.BaseViewModelKT
import com.example.kotlin_test2.util.Loge
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext

open class ResultData<T> {
    var code: Int = 0
    var msg: String = ""
    var result: T? = null

    override fun toString(): String {
        return "ResultData{code:$code,message:$msg,result:${result.toString()}}"
    }
}
open class BaseResponse<T> {
    var message: String? = null
    var code = 0
    var data: T? = null
}

class StartResponse<T> : ResultData<T>()

data class SuccessResponse<T>(var data1: T) : ResultData<T>()

class EmptyResponse<T> : ResultData<T>()

data class FailureResponse<T>(val exception: Throwable) : ResultData<T>()


typealias OnSuccessCallback<T> = (data: T) -> Unit
typealias OnFailureCallback = (e: Throwable) -> Unit
typealias OnUnitCallback = () -> Unit

class HttpRequestCallback<T> {

    var startCallback: OnUnitCallback? = null
    var successCallback: OnSuccessCallback<T>? = null
    var emptyCallback: OnUnitCallback? = null
    var failureCallback: OnFailureCallback? = null
    var finishCallback: OnUnitCallback? = null

    fun onStart(block: OnUnitCallback) {
        startCallback = block
    }

    fun onSuccess(block: OnSuccessCallback<T>) {
        successCallback = block
    }

    fun onEmpty(block: OnUnitCallback) {
        emptyCallback = block
    }

    fun onFailure(block: OnFailureCallback) {
        failureCallback = block
    }

    fun onFinish(block: OnUnitCallback) {
        finishCallback = block
    }
}

interface IStateObserver<T> : Observer<ResultData<T>> {

    override fun onChanged(value: ResultData<T>) {
        when (value) {
            is StartResponse -> {
                //onStart()回调后不能直接就调用onFinish()，必须等待请求结束
                onStart()
                return
            }

            is SuccessResponse -> value.data1?.let { onSuccess(it) }
            is EmptyResponse -> onEmpty()
            is FailureResponse -> onFailure(value.exception)
        }
        onFinish()
    }

    /**
     * 请求开始
     */
    fun onStart()

    /**
     * 请求成功，且 data 不为 null
     */
    fun onSuccess(data: T)

    /**
     * 请求成功，但 data 为 null 或者 data 是集合类型但为空
     */
    fun onEmpty()

    /**
     * 请求失败
     */
    fun onFailure(e: Throwable)

    /**
     * 请求结束
     */
    fun onFinish()
}


fun <T> CoroutineScope.loadHttp(
    // 默认实现{}，即参数可空
    start: () -> Unit = {},
    // suspend 修饰的函数，返回值ResultData<T>，没有默认实现，即参数不可空
    request: suspend CoroutineScope.() -> ResultData<T>,
    // 函数的参数为T，没有默认实现，即参数不可空
    response: (T) -> Unit,
    error: (String, String) -> Unit = { code: String, msg: String -> },
    end: () -> Unit = {}
) {
    // 在主线程(Dispatchers.Main)执行
    launch(Dispatchers.Main) {
        try {
            // 1.函数开始执行，先调用start()方法，给View做准备工作，如：显示loading
            start()
            // 2.发起网络请求
            val data = request()
            if (data.code == 200) {
                // 3.请求成功，返回响应
                if (data.result == null) {
                    data.result = "" as T
                }
                data.result?.let { response(it) }
            } else {
                // 4.请求失败，调用error()
                val msg = if (TextUtils.isEmpty(data.msg)) "Server connection failed!" else data.msg
                error(data.code.toString(), msg)
            }
        } catch (e: Exception) {
            // 可根据具体异常显示具体错误提示
            when (e) {
                is UnknownHostException -> error("400", "Server connection failed!")
                else -> {
                    val msg =
                        if (TextUtils.isEmpty(e.message)) "Server connection failed!" else e.message
                    error("400", msg!!)
                }
            }
        } finally {
            end()
        }
    }
}

fun ViewModel.launchhttp(
    block: suspend CoroutineScope.() -> Unit,
    onError: (e: Throwable) -> Unit = {},
    onComplete: () -> Unit = {}

) {
    viewModelScope.launch(CoroutineExceptionHandler { _, e -> onError(e) }) {
        try {
            block.invoke(this)
        } finally {
            onComplete()
        }

    }

}

fun <T> http(
    context: CoroutineContext = Dispatchers.IO,
    block: suspend () -> ResultData<T>
): LiveData<ResultData<T>> = liveData(context) {
    this.runCatching {
        emit(StartResponse())
        block()
    }.onSuccess {
        emit(
            when (it.code) {
                0, 200 -> {
                    if (it.result == null) {
                        EmptyResponse()
                    } else {
                        SuccessResponse(it.result!!)
                    }
                }

                else -> {
                    Loge.e("网络错误")
                    FailureResponse(Throwable("网络错误"))
                }
            }
        )

    }.onFailure {
        emit(FailureResponse(it))
    }
}


inline fun <T> LiveData<ResultData<T>>.observeState(
    owner: LifecycleOwner,
    crossinline callback: HttpRequestCallback<T>.() -> Unit
) {
    val requestCallback = HttpRequestCallback<T>().apply(callback)
    observe(owner, object : IStateObserver<T> {
        override fun onStart() {
            requestCallback.startCallback?.invoke()
        }

        override fun onSuccess(data: T) {
            requestCallback.successCallback?.invoke(data)
        }

        override fun onEmpty() {
            requestCallback.emptyCallback?.invoke()
        }

        override fun onFailure(e: Throwable) {
            requestCallback.failureCallback?.invoke(e)
        }

        override fun onFinish() {
            requestCallback.finishCallback?.invoke()
        }
    })
}
