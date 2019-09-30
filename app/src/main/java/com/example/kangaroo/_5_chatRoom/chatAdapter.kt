package com.example.kangaroo._5_chatRoom


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kangaroo.R
import kotlinx.android.synthetic.main._5_bot_msg.view.*


class chatAdapter : RecyclerView.Adapter<chatAdapter.MyViewHolder>(){
    private var msgList = mutableListOf<chatMsg.Data>()
    val BOT = 0
    val USER = 1


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val viewBot = LayoutInflater.from(viewGroup.context).inflate(R.layout._5_bot_msg, viewGroup, false)
        val viewUser = LayoutInflater.from(viewGroup.context).inflate(R.layout._5_user_msg, viewGroup, false)
        if(viewType == BOT)
            return MyViewHolder(viewBot)
        else if(viewType == USER)
            return MyViewHolder(viewUser)
        return MyViewHolder(viewBot)
    }



    override fun getItemCount(): Int {
        return msgList.size
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.bind(msgList[position])
    }


    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val msg = view.msg
        fun bind(msgData: chatMsg.Data) {
            msg.text = msgData.msg
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(msgList[position].from == "bot"){
            return BOT
        }else if(msgList[position].from == "user"){
            return USER
        }
        return super.getItemViewType(position)
    }

    fun update(newList: MutableList<chatMsg.Data>) {
        msgList = newList
        notifyDataSetChanged()
    }

}
