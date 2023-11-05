package com.example.kotlin_test2.api

import com.example.kotlin_test2.api.kaiyanapp.TopicBean
import com.example.kotlin_test2.api.tianapi.badboyresultbean.BadBoyResultBean
import com.example.kotlin_test2.api.tianapi.bean.BaseResponse
import com.example.kotlin_test2.api.tianapi.bean.ResultData
import com.example.kotlin_test2.test1.fragment04.paging3.petnewsbean.PetNewsBean
import com.example.kotlin_test2.test1.fragment04.paging3.phonebean.PhoneInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ApiService {
    companion object {
        val BASEURL_BAIDU = "https://www.baidu.com"
        val BASEURL_TIANAPI = "https://apis.tianapi.com/"
        val KEY_TIANAPI = "d96c76cfa9c0f2912413f4b7f1655b2e"
        const val REQUEST_BASE_URL = "http://baobab.kaiyanapp.com/api/"
        val url = "http://www.sogou.com"
    }

    @GET("/")
    suspend fun getBaidu(): ResponseBody

    @GET("zhanan/index")
    suspend fun getBadBoyMSG(@QueryMap map: Map<String, String>): ResultData<BadBoyResultBean>

    @GET("zhanan/index")
    fun getBadBoyMSGCall(@QueryMap map: Map<String, String>): Call<ResponseBody>

    // 首页数据请求
    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    suspend fun getTopicBean(): TopicBean

    // 获取首页第一页之后的数据  ?date=1499043600000&num=2
    @GET("v2/feed")
    suspend fun getMoreTopicBean(
        @Query("date") date: String,
        @Query("num") num: String
    ): TopicBean

    @GET("/petnews/index")
    suspend fun getPetNews(@QueryMap map: Map<String, String>): ResultData<PetNewsBean>

    @GET
    suspend fun phoneAddress(@Url url: String, @Query("number") number: String): BaseResponse<PhoneInfo>

}