package com.example.kotlin_test2.test1.fragment05

import androidx.lifecycle.lifecycleScope
import com.example.kotlin_test2.R
import com.example.kotlin_test2.api.tianapi.bean.observeState
import com.example.kotlin_test2.base.kotlin_base.BaseFragmentKT
import com.example.kotlin_test2.databinding.FragmentPaging2Fragment04Binding
import com.example.kotlin_test2.util.Loge
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class Test1Fragment05 :
    BaseFragmentKT<Test1Fragment05ViewModel, FragmentPaging2Fragment04Binding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_paging2_fragment04
    }

    override fun initView() {
        binding.test1Fragment04Tv.setOnClickListener {
            mViewModel.getBadBoyResultBean02().observeState(requireActivity()) {
                onSuccess {
                    Loge.e(it.content)
                }
                onFailure {
                    Loge.e(it.message)
                }
            }

        }
    }

    override fun initData() {

    }
}