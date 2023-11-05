package com.example.kotlin_test2.api.tianapi

interface ICallback<T> {
    fun onSuccess(obj: T)

    fun onFailed(code: String, msg: String)
}
