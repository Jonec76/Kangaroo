package com.example.kangaroo._3_viewPager


import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.DragEvent
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView

class stopSwipeImage: ImageView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("BT","onTouchEvent $event")
        performClick()
        return true
    }

    override fun onGenericMotionEvent(event: MotionEvent?): Boolean {
        Log.d("BT","inter generic $event")
        return true
    }

    override fun onDragEvent(event: DragEvent?): Boolean {
        Log.d("BT","drag $event")

        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return true;
    }
}