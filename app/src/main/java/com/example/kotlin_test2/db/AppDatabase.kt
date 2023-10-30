package com.example.kotlin_test2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlin_test2.MyApp
import com.example.kotlin_test2.db.bean.Student
import com.example.kotlin_test2.db.bean.Teacher

@Database(entities = [Student::class, Teacher::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @JvmStatic
        val appDatabase: AppDatabase by lazy {
            Room.databaseBuilder(MyApp.getApplication(), AppDatabase::class.java, "appDatabase.db")
                .allowMainThreadQueries()
                .build()
        }
        lateinit var instance: AppDatabase

        fun inst(context: Context): AppDatabase {
            if (!::instance.isInitialized) {
                synchronized(AppDatabase::class) {
                    // 创建数据库
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "instance_database.db"
                    )
                        .allowMainThreadQueries() // Room 原则上不允许在主线程操作数据库
                        // 如果要在主线程操作数据库需要调用该函数
                        .build()
                }
            }
            return instance;
        }
    }
}