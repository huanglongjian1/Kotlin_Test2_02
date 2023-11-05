package com.example.kotlin_test2.test1.fragment04.paging3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kotlin_test2.api.ApiService
import com.example.kotlin_test2.api.kaiyanapp.TopicBean
import com.example.kotlin_test2.base.retrofit.RetrofitBuilder
import com.example.kotlin_test2.util.Loge
import java.util.regex.Pattern
import kotlin.properties.Delegates

class TopicBeanDataSource : PagingSource<Int, TopicBean.IssueListBean.ItemListBean>() {
    override fun getRefreshKey(state: PagingState<Int, TopicBean.IssueListBean.ItemListBean>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopicBean.IssueListBean.ItemListBean> {

        return try {
            val page = params.key ?: 1  //当前页码，若为空则设为1
            Loge.e(page.toString()+"========================")
            val response: TopicBean = when (page) {
                1 -> {
                    RetrofitBuilder.create<ApiService>(ApiService.REQUEST_BASE_URL).getTopicBean()
                }

                else -> {
                    RetrofitBuilder.create<ApiService>(ApiService.REQUEST_BASE_URL)
                        .getMoreTopicBean(System.currentTimeMillis().toString(), "1")
                }
            }  //网络请求数据
            val itemListBean = mutableListOf<TopicBean.IssueListBean.ItemListBean>()
            response.issueList?.forEach {
                it.itemList?.forEach {
                    itemListBean.add(it)
                }
            }

            val prevKey = null   //计算上一页的页码
            val nextKey = if (itemListBean.isNotEmpty()) page + 1 else null //计算下一页的页码


            LoadResult.Page(
                data = itemListBean,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }


    }

    private fun getVideoData(response: TopicBean?): Pair<String?, ArrayList<TopicBean.IssueListBean.ItemListBean>> {
        val nextPageUrl = response?.nextPageUrl
        val date = nextPageUrl?.let { getKey(it) }
        val mList = ArrayList<TopicBean.IssueListBean.ItemListBean>()
        response?.issueList
            ?.flatMap { it.itemList!! }
            ?.filter { it.type.equals("video") }
            ?.forEach { mList.add(it) }
        return Pair(date, mList)
    }

    /**
     * 从url中获取下次请求的date
     */
    private fun getKey(url: String): String {
        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(url)
        val data = m.replaceAll("")
            .subSequence(1, m.replaceAll("").length - 1).toString()
        return data
    }

}