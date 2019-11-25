package com.example.moduleapplication.fragment

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

class MyLinearLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr){

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("ll","${ev?.action}")
        return super.dispatchTouchEvent(ev)
    }
}
