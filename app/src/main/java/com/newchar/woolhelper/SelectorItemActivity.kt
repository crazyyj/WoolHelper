package com.newchar.woolhelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.newchar.woolhelper.adapter.SelectorItemAdapter
import com.newchar.woolhelper.entry.SelectorItem

/**
 *  @author wenliqiang
 *  date 2019-12-15
 *  @since 底部弹出弹窗供上一个页面选择Item的 Activity
 *  @since 迭代版本，（以及描述）
 */

class SelectorItemActivity : AppCompatActivity() {

    var selectorPosition: Int = -1


    private lateinit var rvSelectorItemContainer: RecyclerView
    private lateinit var event_names: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selector_item)
        initRecyclerView(R.id.rvSelectorItemContainer)
        event_names = resources.getStringArray(R.array.event_name)

    }

    private fun initRecyclerView(id: Int) {
        rvSelectorItemContainer = findViewById(id)
        rvSelectorItemContainer.setHasFixedSize(true)
        rvSelectorItemContainer.adapter = SelectorItemAdapter(this)
        (rvSelectorItemContainer.adapter as SelectorItemAdapter).notifyDataSetChanged(castData(event_names))
    }

    /**
     * 需要优化改成使用id 定位选择了哪个
     */
    private fun castData(names: Array<String>): MutableList<SelectorItem> {
        val allData: MutableList<SelectorItem> = ArrayList()
        var temp: SelectorItem
        for (index in 0..names.size) {
            temp = SelectorItem()
            temp.isSelector = index == selectorPosition
            temp.eventName = names[index]
            allData.add(temp)
        }
        return allData
    }


}