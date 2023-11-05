package com.example.kotlin_test2.test1.fragment03

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Looper
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.lifecycle.lifecycleScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.kotlin_test2.MainActivity
import com.example.kotlin_test2.R
import com.example.kotlin_test2.base.kotlin_base.BaseFragmentKT
import com.example.kotlin_test2.databinding.FragmentTest1Fragment03Binding
import com.example.kotlin_test2.util.Loge
import com.google.android.material.snackbar.Snackbar
import com.gyf.immersionbar.ImmersionBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.notify

class Test1Fragment03 : BaseFragmentKT<Test1Fragment03ViewModel, FragmentTest1Fragment03Binding>() {


    override fun getLayoutId(): Int {
        ImmersionBar.with(requireActivity()).init();
        return R.layout.fragment_test1_fragment03
    }

    override fun initView() {
        binding.test1Fragment03Tv.setOnClickListener {

            val request = OneTimeWorkRequest.Builder(MyCoroutineWorker::class.java)
                .setInputData(Data.Builder().putString("data", "昨夜风采").build())
                .build()
            WorkManager.getInstance(requireActivity())

                .enqueue(request)

            WorkManager.getInstance(requireActivity()).getWorkInfoByIdLiveData(request.id)
                .observe(requireActivity()) {
                    it?.let {
                        Loge.e(it.progress.keyValueMap.keys.toString())
                        Loge.e(it.progress.keyValueMap.values.toString())
                    }
                }
        }
    }

    override fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}