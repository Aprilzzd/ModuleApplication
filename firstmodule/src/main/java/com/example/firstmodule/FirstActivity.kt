package com.example.firstmodule

import android.content.pm.ActivityInfo
import android.os.Bundle
import com.example.basemodule.utils.MyUtils
import com.example.firstmodule.fragment.FirstFragment
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator

//@Route(path = Constants.FIRST_FRAGMENT)
class FirstActivity : SupportActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_first)
        MyUtils.setFullStatusBar(this, false)
        if (findFragment(FirstFragment::class.java) == null) {
            loadRootFragment(R.id.rl_root, FirstFragment())
        }
        fragmentAnimator = DefaultHorizontalAnimator()
    }

}
