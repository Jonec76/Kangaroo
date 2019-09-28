package com.example.kangaroo._4_todoList


import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.kangaroo.R
import kotlinx.android.synthetic.main._4_item_note.view.*


class recyclerAdapter : RecyclerView.Adapter<recyclerAdapter.MyViewHolder>(){
    private var noteList = mutableListOf<Holder.Datas>()
    private val ALBUM_CODE = 1
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout._4_item_note, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.bind(noteList[position])
    }



    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvNote = view.tv_note
        val localPhoto = view.localPhoto

        fun bind(newList: Holder.Datas) {
            tvNote.text = newList.text
            Glide.with(view).load(newList.imgURL).into(localPhoto)
        }
    }

    fun update(newList: MutableList<Holder.Datas>) {
        noteList = newList
        notifyDataSetChanged()
    }

}
