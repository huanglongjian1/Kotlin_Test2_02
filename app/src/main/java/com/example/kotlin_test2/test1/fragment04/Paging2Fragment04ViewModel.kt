package com.example.kotlin_test2.test1.fragment04

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.kotlin_test2.api.ApiService
import com.example.kotlin_test2.api.kaiyanapp.TopicBean
import com.example.kotlin_test2.api.tianapi.bean.launchhttp
import com.example.kotlin_test2.api.tianapi.bean.loadHttp
import com.example.kotlin_test2.base.kotlin_base.BaseViewModelKT
import com.example.kotlin_test2.base.retrofit.RetrofitBuilder
import com.example.kotlin_test2.db.AppDatabase
import com.example.kotlin_test2.db.bean.Student
import com.example.kotlin_test2.test1.fragment04.paging3.PetNewsDataSource
import com.example.kotlin_test2.test1.fragment04.paging3.TopicBeanDataSource
import com.example.kotlin_test2.test1.fragment04.paging3.petnewsbean.PetNewsBean
import com.example.kotlin_test2.util.Loge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class Paging2Fragment04ViewModel(application: Application) : BaseViewModelKT(application) {

    val topicBeanDao by lazy {
        AppDatabase.inst(application).getTopicBeanDao()
    }
    val studentDao by lazy {
        AppDatabase.inst(application).getStudentDao()
    }


    fun insertStudent(num: Int) {
        (0..num).map {
            val student = Student("hlj" + it, it)
            studentDao.insertStudent(student)
        }
    }

    fun deleteStudentsAll() {
        studentDao.deleteStudentsAll()
    }

    var index: Int = 0
    fun getStudentsPagingSource(): Flow<PagingData<Student>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = true,
            maxSize = 2000
        ),
        // 通过 room 获取 PagingSource
        pagingSourceFactory = { studentDao.getStudentsPagingSource() }
    ).flow.cachedIn(viewModelScope)

    fun getTopicBeansPagingSource(): Flow<PagingData<TopicBean.IssueListBean.ItemListBean>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = true,
            maxSize = 30
        ),
        pagingSourceFactory = {
            TopicBeanDataSource()
        }
    ).flow.cachedIn(viewModelScope)

    fun getPetNews(): Flow<PagingData<PetNewsBean.NewslistBean>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = true,
            maxSize = 2000
        ),
        pagingSourceFactory = {
            PetNewsDataSource()
        }
    ).flow.cachedIn(viewModelScope)
}