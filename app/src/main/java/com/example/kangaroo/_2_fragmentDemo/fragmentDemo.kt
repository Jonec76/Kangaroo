package com.example.kangaroo._2_fragmentDemo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.kangaroo.R
import kotlinx.android.synthetic.main._2_fragment_demo.*
import kotlinx.android.synthetic.main._2_fragment_demo.view.*

class fragmentDemo : Fragment() {
    var outState = Bundle()
    val SPIN_KEY = "spin"
    val FM_REPLACE_KEY = "replace"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("state", "onCreateView")
        val view = inflater.inflate(R.layout._2_fragment_demo, container, false)
        if(outState != null){
            Log.d("state", "get bundle saved")
            Log.d("bundle", outState.toString())
            view.show_input.text = outState.getString(FM_REPLACE_KEY)
        }
        if(savedInstanceState != null){
            Log.d("state", "get bundle savedInstanceState")
            Log.d("bundle", savedInstanceState.toString())
            view.show_input.text = savedInstanceState.getString(FM_REPLACE_KEY)
        }
        view.input_edit.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE){
                view.input_edit.clearFocus();
                val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.input_edit.getWindowToken(), 0)
                true
            }
            false
        }
        view.submit.setOnClickListener {
            view.show_input.text = view.input_edit.text
            view.input_edit.text.clear()
            view.input_edit.clearFocus()
            val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.input_edit.getWindowToken(), 0)
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        Log.d("state", "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("state", "onStop")
        Log.d("value", show_input?.text.toString())
        saveBundle()
    }

    override fun onDestroy() {
        Log.d("state", "onDestroy")
        super.onDestroy()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("state", "onSaveInstanceState")
        outState.putString(SPIN_KEY, show_input?.text.toString())
    }

    fun saveBundle(){
        outState.putString(FM_REPLACE_KEY, show_input?.text.toString())
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("state", "onActivityCreated")
        val spinValue = savedInstanceState?.getString(SPIN_KEY)
        if(spinValue != null){
            show_input.text = spinValue
            Log.d("restore data", spinValue)
        }
    }
}