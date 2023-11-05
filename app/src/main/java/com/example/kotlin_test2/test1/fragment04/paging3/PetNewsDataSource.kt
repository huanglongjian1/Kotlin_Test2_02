package com.example.kotlin_test2.test1.fragment04.paging3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kotlin_test2.api.ApiService
import com.example.kotlin_test2.api.tianapi.bean.ResultData
import com.example.kotlin_test2.base.retrofit.RetrofitBuilder
import com.example.kotlin_test2.test1.fragment04.paging3.petnewsbean.PetNewsBean
import com.example.kotlin_test2.util.Loge

class PetNewsDataSource : PagingSource<Int, PetNewsBean.NewslistBean>() {
    override fun getRefreshKey(state: PagingState<Int, PetNewsBean.NewslistBean>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PetNewsBean.NewslistBean> {
        try {
            // 获取请求的页数
            val page = params.key ?: 1
            val map = mutableMapOf<String, String>()
            //  Loge.e(params.loadSize.toString()+":::::"+page.toString()+"-***********************")
            map["key"] = ApiService.KEY_TIANAPI
            map["num"] = params.loadSize.toString()
            map["page"] = page.toString()

            Loge.e(map.toString())

            var listItems = mutableListOf<PetNewsBean.NewslistBean>()
            kotlin.runCatching {
                RetrofitBuilder.create<ApiService>(ApiService.BASEURL_TIANAPI).getPetNews(map)
            }.onSuccess {
                it.result?.newslist?.forEach {
                    listItems.add(it)
                }
            }.onFailure {
                Loge.e(it.message)
            }

            // 设置前一页和下一页的信息
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (listItems.isNotEmpty()) page + 1 else null
            return LoadResult.Page(
                data = listItems,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

    }
}