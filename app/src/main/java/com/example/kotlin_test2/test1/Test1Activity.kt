package com.example.kotlin_test2.test1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.kotlin_test2.R
import com.example.kotlin_test2.base.kotlin_base.BaseActivityKT
import com.example.kotlin_test2.databinding.Test1ActivityBinding
import com.example.kotlin_test2.util.Constants
import com.example.kotlin_test2.util.Loge

@Route(path = Constants.PATH_TEST1_ACTIVITY)
class Test1Activity : BaseActivityKT<Test1ViewModel, Test1ActivityBinding>() {


    override fun getContentViewId(): Int {
        return R.layout.test1_activity
    }

    override fun initView(savedInstanceState: Bundle?) {
        setSupportActionBar(binding.navigateToolbar)
        val navController = findNavController(R.id.test1_activity_navhost_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.test1Fragment01, R.id.test1Fragment02, R.id.test1Fragment03,
                R.id.test1Fragment04,R.id.test1Fragment05

            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.test1ActivityBottomNavigationView.setupWithNavController(navController)

        binding.navigateToolbar.setNavigationOnClickListener {
            Loge.e("back")
        }
        binding.navigateToolbar.title = "紧急通知"
    }

    override fun initData() {
        binding.test1Tv.setOnClickListener {

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.test1_activity_navhost_fragment).navigateUp()
    }

}