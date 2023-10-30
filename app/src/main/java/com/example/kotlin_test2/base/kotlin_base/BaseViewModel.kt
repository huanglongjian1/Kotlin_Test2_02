package com.example.kotlin_test2.base.kotlin_base

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_test2.base.BaseViewModel
import com.example.mvvm.bean.LoadState

open class BaseViewModelKT(application: Application) : BaseViewModel(application) {
    // 加载状态
    val loadState = MutableLiveData<LoadState>()
}