package com.example.kangaroo._4_todoList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kangaroo.R
import kotlinx.android.synthetic.main._4_todo_list.view.*

class todoList: Fragment() {
    val recyclerAdapter =  recyclerAdapter()
    private var holderList = mutableListOf<Holder.Datas>()
    val NEW_DATA = "new"
    val NEW_IMG = "img"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("status", "onCreateView")
        if(arguments?.getString(NEW_DATA) != null){
            val newTxt = arguments?.getString(NEW_DATA)
            holderList.add(Holder.Datas(null, newTxt))
            recyclerAdapter.update(holderList)
        }

        if(arguments?.getParcelableArrayList<Holder.Datas>(NEW_IMG) != null){
            val newTxt = arguments?.getParcelableArrayList<Holder.Datas>(NEW_IMG)
            for(i in 0..newTxt!!.size-1){
                holderList.add(newTxt[i])
            }
            recyclerAdapter.update(holderList)
        }
        val view = inflater.inflate(R.layout._4_todo_list, container, false)
        val fm = activity?.supportFragmentManager
        view.rv.adapter = recyclerAdapter

        view.toEdit.setOnClickListener {
            fm?.beginTransaction()
                ?.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                ?.replace(R.id.loginFrame, editFragment(), "editList")
                ?.addToBackStack(null)
                ?.commit()
        }
        recyclerAdapter.update(holderList)

        return view
    }



    override fun onStop() {
        super.onStop()
        Log.d("status", "onStop")
    }

    override fun onResume() {
        super.onResume()
        Log.d("status", "onResume")

    }
}