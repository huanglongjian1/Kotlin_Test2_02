package com.example.kotlin_test2.test1.fragment03

import android.app.Notification
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


class MyCoroutineWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    companion object {
        const val Progress = "Progress"
        private const val delayDuration = 1L
    }

    override suspend fun doWork(): Result {

        var pr = inputData.getString("data") ?: "-"

        for (i in 0..100) {
            val data = workDataOf(pr to i)
            delay(100)
            setProgress(data)
        }

        return Result.Success.success()
    }


    override suspend fun getForegroundInfo(): ForegroundInfo {

        val id = Random.nextInt(0, Int.MAX_VALUE)
        return ForegroundInfo(id, getNotion())


    }


    private fun getNotion(): Notification {
        val notification = Notification()
        return notification;
    }
}
