package com.example.kangaroo._3_viewPager

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class pagerAdapter(fragmentManager: FragmentManager, private val movies: ArrayList<datas>) :
    FragmentStatePagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        Log.d("", "in newInstance")
        when(position){
            0->return pageFragment.newInstance(movies[position])
            1->return pageFragment.newInstance(movies[position])
            2->return pageFragment.newInstance(movies[position])
            else->return pageFragment.newInstance(movies[position])
        }
    }

    override fun getCount(): Int {
        return movies.size
    }
}