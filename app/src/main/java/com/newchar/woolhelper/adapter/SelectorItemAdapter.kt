package com.newchar.woolhelper.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.newchar.woolhelper.R
import com.newchar.woolhelper.entry.SelectorItem

/**
 *  @author wenliqiang
 *  date 2019-12-15
 *  @since 当前版本，（以及描述）
 *  @since 迭代版本，（以及描述）
 */
class SelectorItemAdapter(ctx: Context) : RecyclerView.Adapter<SelectorItemAdapter.ViewHolder>() {

    private val adapterData: MutableList<Any>
    private val layoutInflater: LayoutInflater

    init {
        adapterData = ArrayList()
        layoutInflater = ctx.getSystemService()!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_selector_content, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if (adapterData.isNullOrEmpty()) {
            0
        } else {
            adapterData.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    fun notifyDataSetChanged(mutable: MutableList<SelectorItem>) {
        adapterData.addAll(mutable)
        notifyDataSetChanged()
    }

    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

}