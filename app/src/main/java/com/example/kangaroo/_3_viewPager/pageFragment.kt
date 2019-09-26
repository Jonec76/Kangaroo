package com.example.kangaroo._3_viewPager

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.kangaroo.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.page_fragment.view.*

class pageFragment : Fragment() {
    private lateinit var v: View
    val FRAG_DATAS = "1"
    private lateinit var fragTitle: String

    companion object {
        fun newInstance(message: datas): pageFragment {
            Log.d("", message.titleText)
            val f = pageFragment()
            f.fragTitle = message.titleText
            val bdl = Bundle()
            bdl.putParcelable(f.FRAG_DATAS, message)
            f.arguments = bdl
            return f
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.page_fragment, container, false)
        v.title.text = fragTitle
        return v
    }
}