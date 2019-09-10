package com.example.moduleapplication.fragment

import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Button
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.example.apt_annotation.BindView
import com.example.apt_annotation.OnClick
import com.example.apt_library.BindViewByPoetTools
import com.example.basemodule.base.BaseContract
import com.example.basemodule.base.BaseFragment
import com.example.basemodule.base.BasePresenter
import com.example.basemodule.utils.AppExecutors
import com.example.basemodule.utils.Constants
import com.example.moduleapplication.R
import java.lang.ref.WeakReference
import java.util.concurrent.ExecutorService

@Route(path = Constants.APT_FRAGMENT)
class AptTestFragment : BaseFragment<BasePresenter<BaseContract.BaseView>>() {

    private lateinit var executor: ExecutorService
    @BindView(R.id.btn) lateinit var btn:Button
    private val handler=MyHandler(this)

    override fun getLayoutId(): Int {
        return R.layout.fragment_apt_test
    }

    override fun initView(p0: View?) {
        BindViewByPoetTools.bind(this,p0)

        executor = AppExecutors.singleExecutor()
    }

    override fun initPresenter() {

    }

    @OnClick(R.id.btn)
    public fun onClick(view: View){
        ToastUtils.showShort("haha")
        for (x in 1..5){
            val runnable= Runnable {
                println("正在执行task$x")
                Thread.sleep(5000)
                println("task${x}执行完毕")
                btn.post { Runnable {
//                    btn.text=x.toString()
                    println("--")
                } }
                val message=Message()
                message.obj=x
                handler.sendMessage(message)
            }
            executor.execute(runnable)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    class MyHandler(fragment: AptTestFragment) : Handler() {

        private var mFragment= WeakReference(fragment)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (mFragment.get()!=null){
                mFragment.get()!!.btn.text=msg.obj.toString()
            }
        }
    }

}