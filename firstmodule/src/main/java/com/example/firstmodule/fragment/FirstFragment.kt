package com.example.firstmodule.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.example.basemodule.base.BaseContract
import com.example.basemodule.base.BaseFragment
import com.example.basemodule.base.BasePresenter
import com.example.basemodule.utils.Constants
import com.example.firstmodule.BuildConfig
import com.example.firstmodule.MyAdapter
import com.example.firstmodule.R
import kotlinx.android.synthetic.main.fragment_first.*

@Route(path = Constants.FIRST_FRAGMENT)
class FirstFragment : BaseFragment<BasePresenter<BaseContract.BaseView>>() {

    @Autowired
    lateinit var name: String
    private val list= ArrayList<String>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_first
    }

    override fun initPresenter() {

    }

    override fun initView(view: View?) {
        tv.text= BuildConfig.DEBUG.toString()
        Glide.with(this).load("https://pics1.baidu.com/feed/f603918fa0ec08fa6e5b839e697d216854fbdacc.jpeg?token=e872c7fd6f2d3d6271deba73360dad40&s=B9B3089C56225ABCC91870C90300E099")
            .into(iv)
        val bundle = Bundle()
        bundle.putString("key","123")
        setFragmentResult(1,bundle)

        for (x in 1..20){
            list.add(x.toString())
        }
        rv.layoutManager=LinearLayoutManager(_mActivity)
        rv.adapter= MyAdapter(list)
        rv.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                Log.e("--",newState.toString())
            }
        })

        var str= StringBuilder("123")

    }

}