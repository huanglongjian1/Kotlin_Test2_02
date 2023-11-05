package com.example.kotlin_test2.test1.fragment04.paging3.petnewsadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_test2.R
import com.example.kotlin_test2.test1.fragment04.paging3.petnewsbean.PetNewsBean
import com.example.kotlin_test2.test1.fragment04.paging3.petnewsbean.PetNewsBean.NewslistBean
import com.example.kotlin_test2.util.Loge

class PetNewsAdapter :
    PagingDataAdapter<PetNewsBean.NewslistBean, NewslistBeanVH>(NewslistBeanDiff) {
    object NewslistBeanDiff : DiffUtil.ItemCallback<NewslistBean>() {
        override fun areItemsTheSame(oldItem: NewslistBean, newItem: NewslistBean): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NewslistBean, newItem: NewslistBean): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onBindViewHolder(holder: NewslistBeanVH, position: Int) {
        holder.textView.text = getItem(position)?.description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewslistBeanVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment04_rv_item, null)
        return NewslistBeanVH(view)
    }
}

class NewslistBeanVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView = itemView.findViewById<TextView>(R.id.fragment04_rv_item_tv)
}