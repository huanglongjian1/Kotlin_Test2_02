package com.example.kotlin_test2.test1.fragment04.paging3

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.kotlin_test2.R
import com.example.kotlin_test2.api.kaiyanapp.TopicBean

class TopicBeanAdapter_noPaging:BaseQuickAdapter<TopicBean.IssueListBean.ItemListBean,BaseViewHolder>(
    R.layout.fragment04_rv_item) {
    override fun convert(holder: BaseViewHolder, item: TopicBean.IssueListBean.ItemListBean) {
        holder.setText(R.id.fragment04_rv_item_tv,item.toString())
    }
}