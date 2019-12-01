package com.newchar.woolhelper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newchar.woolhelper.R
import com.newchar.woolhelper.entry.ActionEntry

/**
 *  @author wenliqiang
 *  date 2019-12-01
 *  @since v0.1 事件是否开启的列表, 暂定放在首页
 *  @since 迭代版本，（以及描述）
 */
class ActionListAdapter(var list: List<ActionEntry>) : RecyclerView.Adapter<ActionListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.item_main_action_list_common, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list?.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}