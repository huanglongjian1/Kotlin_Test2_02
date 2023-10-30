package com.example.kotlin_test2.base.kotlin_base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_test2.base.ActivityManager
import com.example.kotlin_test2.base.BaseViewModel

abstract class BaseActivityKT<VM : BaseViewModel, VDB : ViewDataBinding> : AppCompatActivity() {
    val binding: VDB by lazy {
        DataBindingUtil.setContentView(this, getContentViewId())
    }
    protected val mViewModel by lazy {
        ViewModelProvider(this)[createViewModel()]
    }

    override fun onDestroy() {
        ActivityManager.getInstance().removeActivity(this)
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = javaClass.simpleName
        ActivityManager.getInstance().addActivity(this);
        setContentView(binding.root);
        binding.lifecycleOwner = this;
        initData()
        initView(savedInstanceState)
    }

    abstract fun createViewModel(): Class<VM>
    abstract fun getContentViewId(): Int
    abstract fun initData()
    abstract fun initView(savedInstanceState: Bundle?)

}

