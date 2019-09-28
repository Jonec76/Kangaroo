package com.example.kangaroo._4_todoList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kangaroo.R
import kotlinx.android.synthetic.main._4_edit_fragment.view.*



class editFragment:Fragment() {
    val ALBUM_CODE = 1
    val NEW_DATA = "new"
    val NEW_IMG = "img"
    private var noteList = mutableListOf<Holder.Datas>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout._4_edit_fragment, container, false)
        val args = Bundle()

        view.submit.setOnClickListener {
            args.putString(NEW_DATA, view.input_data.text.toString())
            val todoList = fragmentManager!!.findFragmentByTag("todoList")
            todoList!!.arguments = args
            fragmentManager?.popBackStack()
        }

        view.addImg.setOnClickListener {
            choosePhotoEvent()
        }


        return view

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            ALBUM_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val args = Bundle()
                    val uri = data?.data
                    for(i in 0..(data.clipData!!.itemCount-1)){
                        noteList.add(Holder.Datas(data?.clipData?.getItemAt(i)?.uri, "hi"))
                    }
                    args.putParcelableArrayList( NEW_IMG,ArrayList<Holder.Datas>(noteList))
                    val todoList = fragmentManager!!.findFragmentByTag("todoList")
                    todoList!!.arguments = args
                    fragmentManager?.popBackStack()
                }
            }
        }
    }

    private fun choosePhotoEvent(){
        val intentAlbum = Intent(Intent.ACTION_PICK)
        intentAlbum.type = "image/*"
        intentAlbum.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intentAlbum,ALBUM_CODE)
    }
}