package com.example.kotlin_test2.test1.fragment04.paging3.studentadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_test2.R
import com.example.kotlin_test2.db.bean.Student

class StudentAdapter : PagingDataAdapter<Student, StudentVH>(StudentDiff) {
    object StudentDiff : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.uid == newItem.uid
        }

    }

    override fun onBindViewHolder(holder: StudentVH, position: Int) {
        holder.textView.text = getItem(position)?.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment04_rv_item, null)
        return StudentVH(view)
    }
}

class StudentVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView = itemView.findViewById<TextView>(R.id.fragment04_rv_item_tv)
}