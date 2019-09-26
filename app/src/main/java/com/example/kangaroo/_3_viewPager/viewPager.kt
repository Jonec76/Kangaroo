package com.example.kangaroo._3_viewPager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.kangaroo.R
import com.example.kangaroo._3_viewPager.viewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main._3_view_pager.*
import kotlinx.android.synthetic.main._3_view_pager.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.nav_view

class viewPager:Fragment() {
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewPager.setCurrentItem(0, true)
                return@OnNavigationItemSelectedListener true

            }
            R.id.navigation_dashboard -> {
                viewPager.setCurrentItem(1, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                viewPager.setCurrentItem(2, true)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout._3_view_pager, container, false)
        val fm = fragmentManager!!
        val datas = ArrayList<datas>()
        datas.add(datas("Titanic"))
        datas.add(datas("It"))
        datas.add(datas("Tree"))
        view.bottom_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        view.imgView.setOnTouchListener { view, motionEvent ->
            when(motionEvent.action){
                MotionEvent.ACTION_DOWN ->view.parent.requestDisallowInterceptTouchEvent(true)
                MotionEvent.ACTION_UP ->view.parent.requestDisallowInterceptTouchEvent(false)
            }
            return@setOnTouchListener true
        }
        view.viewPager.adapter = pagerAdapter(fm, datas)
        view.viewPager.addOnPageChangeListener(pageListener())
        return view
    }
    inner class pageListener: ViewPager.OnPageChangeListener{

        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }
        override fun onPageSelected(position: Int) {
            when(position) {
                0 -> {bottom_view.selectedItemId = R.id.navigation_home;Log.d("", "00000")}
                1 -> bottom_view.selectedItemId = R.id.navigation_dashboard
                2 -> bottom_view.selectedItemId = R.id.navigation_notifications
            }
        }

    }
}