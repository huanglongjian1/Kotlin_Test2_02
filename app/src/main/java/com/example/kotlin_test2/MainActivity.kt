package com.example.kotlin_test2

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.kotlin_test2.base.BaseActivity
import com.example.kotlin_test2.base.BaseViewModel
import com.example.kotlin_test2.base.kotlin_base.BaseActivityKT
import com.example.kotlin_test2.databinding.ActivityMainBinding
import com.example.kotlin_test2.util.Constants
import com.example.kotlin_test2.util.Loge
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar

@Route(path = Constants.PATH_MAIN_ACTIVITY)
class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {


    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.mianTv.setOnClickListener {
            Loge.e("first")
        }
        binding.gotoNext.setOnClickListener {
            ARouter.getInstance().build(Constants.PATH_TEST1_ACTIVITY).navigation()
        }
    }

    override fun initData() {
        val immersionBar = ImmersionBar.with(this);
        immersionBar.hideBar(BarHide.FLAG_SHOW_BAR).init(); //隐藏状态栏或导航栏或两者，不写默认不隐藏
    }
}