package com.example.moduleapplication

import android.content.pm.ActivityInfo
import android.os.Bundle
import com.example.basemodule.utils.MyUtils
import com.example.moduleapplication.fragment.MainFragment
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator

class MainActivity : SupportActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)
        MyUtils.setFullStatusBar(this, false)
        if (findFragment(MainFragment::class.java) == null) {
            loadRootFragment(R.id.rl_root, MainFragment())
        }
        fragmentAnimator = DefaultHorizontalAnimator()
    }

}
