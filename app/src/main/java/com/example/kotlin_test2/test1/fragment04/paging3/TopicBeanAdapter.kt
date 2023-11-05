package com.example.kotlin_test2.test1.fragment04.paging3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_test2.R
import com.example.kotlin_test2.api.kaiyanapp.TopicBean
import com.example.kotlin_test2.util.Loge

class TopicBeanAdapter :
    PagingDataAdapter<TopicBean.IssueListBean.ItemListBean, TopicBeanVH>(DataDifferntiator) {
    override fun onBindViewHolder(holder: TopicBeanVH, position: Int) {
        Loge.e("========================================================")
        holder.textView.text = getItem(position)?.data?.description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicBeanVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment04_rv_item, null)
        return TopicBeanVH(view)
    }

    object DataDifferntiator : DiffUtil.ItemCallback<TopicBean.IssueListBean.ItemListBean>() {
        override fun areItemsTheSame(
            oldItem: TopicBean.IssueListBean.ItemListBean,
            newItem: TopicBean.IssueListBean.ItemListBean
        ): Boolean {
            return oldItem.data == newItem.data
        }

        override fun areContentsTheSame(
            oldItem: TopicBean.IssueListBean.ItemListBean,
            newItem: TopicBean.IssueListBean.ItemListBean
        ): Boolean {
            return oldItem.data?.id == newItem.data?.id
        }

    }
}

class TopicBeanVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView = itemView.findViewById<TextView>(R.id.fragment04_rv_item_tv)
}