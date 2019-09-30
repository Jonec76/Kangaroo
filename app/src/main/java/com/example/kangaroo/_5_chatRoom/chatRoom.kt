package com.example.kangaroo._5_chatRoom

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.kangaroo.R
import kotlinx.android.synthetic.main._5_chat_room.view.*

class chatRoom(): Fragment() {
    private var msgList = mutableListOf<chatMsg.Data>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var viewRoom = inflater.inflate(R.layout._5_chat_room, container, false)
        val chatAdapter = chatAdapter()
        viewRoom.chatRoom.setOnTouchListener { view, motionEvent ->
            when(motionEvent.action) {
                MotionEvent.ACTION_DOWN-> {
                    Log.d("", "down")
                    viewRoom.texting.clearFocus()
                    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view!!.windowToken, 0)
                    return@setOnTouchListener false
                }
                MotionEvent.ACTION_UP->{
                    Log.d("", "up")
                    return@setOnTouchListener false
                }
                MotionEvent.ACTION_MOVE->{
                    Log.d("", "move")
                    return@setOnTouchListener false
                }
            }
            return@setOnTouchListener false
        }
        viewRoom.chatRoom.adapter = chatAdapter
        viewRoom.chatRoom.scrollBarSize = 10
        msgList.add(chatMsg.Data("bot", "I'm bot"))
        msgList.add(chatMsg.Data("user", "I'm user"))
        msgList.add(chatMsg.Data("bot", "hello"))
        chatAdapter.update(msgList)
        viewRoom.texting.setOnTouchListener { view, motionEvent ->
            viewRoom.chatRoom.smoothScrollToPosition(msgList.size-1)
            return@setOnTouchListener false
        }
        viewRoom.sendBtn.setOnTouchListener { view, motionEvent ->
            if(viewRoom.texting.text.toString() != "") {
                msgList.add(chatMsg.Data("user", viewRoom.texting.text.toString()))
                chatAdapter.update(msgList)
                viewRoom.chatRoom.smoothScrollToPosition(msgList.size-1)
                viewRoom.texting.text.clear()
            }
            return@setOnTouchListener false
        }



        return viewRoom
//        return super.onCreateView(inflater, container, savedInstanceState)
    }
}