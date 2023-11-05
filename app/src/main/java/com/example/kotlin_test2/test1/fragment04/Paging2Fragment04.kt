package com.example.kotlin_test2.test1.fragment04

import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_test2.R
import com.example.kotlin_test2.api.ApiService
import com.example.kotlin_test2.api.tianapi.bean.http
import com.example.kotlin_test2.api.tianapi.bean.observeState
import com.example.kotlin_test2.base.kotlin_base.BaseFragmentKT
import com.example.kotlin_test2.base.retrofit.RetrofitBuilder
import com.example.kotlin_test2.databinding.FragmentPaging2Fragment04Binding
import com.example.kotlin_test2.test1.fragment04.paging3.TopicBeanAdapter
import com.example.kotlin_test2.test1.fragment04.paging3.TopicBeanAdapter_noPaging
import com.example.kotlin_test2.test1.fragment04.paging3.petnewsadapter.PetNewsAdapter
import com.example.kotlin_test2.test1.fragment04.paging3.studentadapter.StudentAdapter
import com.example.kotlin_test2.util.Loge
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class Paging2Fragment04 :
    BaseFragmentKT<Paging2Fragment04ViewModel, FragmentPaging2Fragment04Binding>() {

    companion object {
        fun newInstance() = Paging2Fragment04()
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_paging2_fragment04
    }

    override fun initView() {
        binding.test1Fragment04Tv.setOnClickListener {

            mViewModel.insertStudent(10)
        }
        binding.test1Fragment04Clear.setOnClickListener {

        }
        val adapter = StudentAdapter()
        lifecycleScope.launch {

            binding.test1Fragment04Rv.adapter = adapter
            binding.test1Fragment04Rv.layoutManager = LinearLayoutManager(requireContext())
            mViewModel.getStudentsPagingSource().collectLatest {
                adapter.submitData(it)
            }

        }
        binding.test1Fragment04SwipeRefreshLayout.setOnRefreshListener {
            lifecycleScope.launch {
                adapter.refresh()
                binding.test1Fragment04SwipeRefreshLayout.isRefreshing = false
            }
        }
        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Loading -> {
                    Loge.e("正在加载")
                }

                is LoadState.NotLoading -> {
                    Loge.e("加载完成")
                }

                is LoadState.Error -> {
                    Loge.e("加载错误")
                }
            }
            when (it.append) {

                else -> {
                    Loge.e("加载更多")
                }
            }
        }

    }


    override fun initData() {

    }


}