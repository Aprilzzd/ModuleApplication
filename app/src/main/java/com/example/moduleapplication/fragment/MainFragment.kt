package com.example.moduleapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.example.basemodule.base.BaseContract
import com.example.basemodule.base.BaseFragment
import com.example.basemodule.base.BasePresenter
import com.example.basemodule.utils.Constants
import com.example.moduleapplication.BuildConfig
import com.example.moduleapplication.R
import kotlinx.android.synthetic.main.fragment_main.*
import me.yokeyword.fragmentation.ISupportFragment

class MainFragment : BaseFragment<BasePresenter<BaseContract.BaseView>>() {

    private var exitTime: Long = 0

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initPresenter() {
        
    }

    override fun initView(view: View) {
        tv.text= BuildConfig.DEBUG.toString()
        btn.setOnClickListener {
            startForResult(
                ARouter.getInstance().build(Constants.APT_FRAGMENT)
                    .withLong("key1", 666L)
                    .withString("name", "888")
                    .navigation() as ISupportFragment?,1
            )
            try {
                var cla=Class.forName("com.alibaba.android.arouter.core.Warehouse")
                val field=cla.getDeclaredField("routes")
                field.isAccessible=true
                Log.e("==",field.get(cla).toString())
            } catch (e: ClassNotFoundException) {
                Log.e("==",e.message)
                e.printStackTrace()
            } catch (e: NoSuchFieldException) {
                Log.e("==",e.message)
                e.printStackTrace()
            }

        }
    }

    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {
        super.onFragmentResult(requestCode, resultCode, data)
        if (requestCode==1)
            ToastUtils.showShort(data?.getString("key"))
    }

    override fun onBackPressedSupport(): Boolean {
        return if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtils.showShort("再按一次退出")
            exitTime = System.currentTimeMillis()
            true
        } else {
            super.onBackPressedSupport()
        }
    }
}
