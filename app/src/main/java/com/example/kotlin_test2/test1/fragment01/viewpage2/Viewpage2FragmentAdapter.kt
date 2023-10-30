package com.example.kotlin_test2.test1.fragment01.viewpage2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class Viewpage2FragmentAdapter(val activity: FragmentActivity, val fragments: MutableList<Fragment>): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
       return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
      return fragments[position]
    }
}