package com.hxz.banner.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hxz.banner.utils.BannerUtils

abstract class BaseBannerAdapter<T>(data: MutableList<T> = arrayListOf()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataList : MutableList<T> = data
    private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(createView(parent, viewType)){}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            BannerUtils.clickListener?.invoke(BannerUtils.getRealPosition(position,dataList.size))
        }
        if (dataList.isNotEmpty()) convertHolder(holder,dataList[BannerUtils.getRealPosition(position,dataList.size)])
    }

    override fun getItemCount(): Int {
        return if (BannerUtils.isCanLoop) BannerUtils.MAX_VALUE_SIZE else dataList.size
    }

    fun setData(list: MutableList<T>) {
        dataList = list
        notifyDataSetChanged()
    }

    fun addData(t: T) {
        dataList.add(t)
        notifyItemChanged(dataList.size)
    }

    abstract fun createView(parent: ViewGroup,viewType: Int) : View

    abstract fun convertHolder(holder: RecyclerView.ViewHolder,item: T)
}