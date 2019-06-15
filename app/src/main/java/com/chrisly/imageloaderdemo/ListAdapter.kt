package com.chrisly.imageloaderdemo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrisly.imageloaderdemo.glide.SimpleGlide
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.ArrayList

/**
 * @author big insect
 * @date 2019/6/3.
 */
class ListAdapter(private val mContext: Context) : RecyclerView.Adapter<ListAdapter.ViewHolder>(){

    private var urlList: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  urlList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView){
            SimpleGlide.with(context = mContext)
                .load(urlList[position])
                .into(image)
        }
    }

    fun addData(list: List<String>){
        urlList.clear()
        urlList.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}