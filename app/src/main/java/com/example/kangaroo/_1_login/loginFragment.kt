package com.example.kangaroo._1_login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.content.Intent
import android.view.inputmethod.EditorInfo
import com.example.kangaroo.R
import kotlinx.android.synthetic.main._1_login.view.*
import android.content.Context.INPUT_METHOD_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.view.inputmethod.InputMethodManager


//1
class loginFragment : Fragment() {
    //2

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout._1_login, container, false)
        view.accountSubmit.setOnClickListener {
            val account = view.account.text.toString()
            val password = view.password.text.toString()
            if(account == "admin" && password == "admin"){
                Toast.makeText(context, "登入成功", Toast.LENGTH_SHORT).show()
                view.img_lock.setImageResource(R.drawable.ic_lock_open_black_24dp)
            }else{
                Toast.makeText(context, "登入失敗", Toast.LENGTH_SHORT).show()
            }
            view.account.text.clear()
            view.password.text.clear()
        }
//        view.password.requestFocus()

        view.account.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE){
                view.account.clearFocus();
                val imm = activity!!.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.account.getWindowToken(), 0)
                true
            }
            false
        }
        view.password.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE){
                view.password.clearFocus();
                val imm = activity!!.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.password.getWindowToken(), 0)
                true
            }
            false
        }
        return view
    }
}