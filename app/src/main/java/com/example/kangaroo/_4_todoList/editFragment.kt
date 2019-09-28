package com.example.kangaroo._4_todoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kangaroo.R
import kotlinx.android.synthetic.main._4_edit_fragment.view.*



class editFragment:Fragment() {

    val NEW_DATA = "new"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout._4_edit_fragment, container, false)
        val args = Bundle()

        view.submit.setOnClickListener {
            args.putString(NEW_DATA, view.input_data.text.toString())
            val todoList = fragmentManager!!.findFragmentByTag("todoList")
            todoList!!.arguments = args
            fragmentManager?.popBackStack()
        }
        return view

    }
}