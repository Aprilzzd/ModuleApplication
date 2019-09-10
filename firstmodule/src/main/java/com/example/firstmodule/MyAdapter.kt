package com.example.firstmodule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv.view.*

class MyAdapter(private val list:ArrayList<String>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_rv,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class MyViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bind(item:String){
            itemView.tv_item.text= item
        }
    }
}