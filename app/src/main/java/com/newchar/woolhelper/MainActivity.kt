package com.newchar.woolhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.newchar.accesshelper.AccessManager
import com.newchar.woolhelper.fragment.EditActionFragment

class MainActivity : AppCompatActivity() {

    lateinit var rvMainActionList: RecyclerView
    lateinit var fabMainCreateAction: AppCompatTextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 展开服务列表， 是否开启服务。
        fabMainCreateAction = findViewById(R.id.fabMainCreateAction)
        rvMainActionList = findViewById(R.id.rvMainActionList)
        rvMainActionList.setHasFixedSize(true)

        fabMainCreateAction.setOnClickListener { EditActionFragment.actionLaunch("") }

        //TODO 搜索本地的配置， 显示成列表

//        AccessManager(this).init()
    }

}
