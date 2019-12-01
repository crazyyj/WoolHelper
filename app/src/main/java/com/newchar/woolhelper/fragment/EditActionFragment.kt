package com.newchar.woolhelper.fragment

import android.media.AudioRouting
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.newchar.woolhelper.R
import com.newchar.woolhelper.RouterNav
import com.newchar.woolhelper.entry.ActionEntry

/**
 *  @author wenliqiang
 *  date 2019-12-01
 *  @since 读取数据库中的事件配置用来显示
 *  @since 迭代版本，（以及描述）
 */
class EditActionFragment : Fragment() {

    var id: String? = ""
    val action = ActionEntry()
    lateinit var cbEditActionForceFlag: AppCompatCheckBox
    lateinit var etEditActionClassName: AppCompatEditText
    lateinit var etCreateActionAppPackageName: AppCompatEditText
    lateinit var etEditActionActionForText: AppCompatTextView
    lateinit var etEditActionActionForId: AppCompatTextView

    companion object {
        fun actionLaunch(id: String): EditActionFragment {
            val fragment = EditActionFragment()
            if (!TextUtils.isEmpty(id)) {
                val bundle: Bundle = Bundle()
                bundle.putString("ID", id)
                fragment.arguments = bundle
            }
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getString("ID")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_action, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tvEditActionReadButton)
            .setOnClickListener(this::onViewClick)
        view.findViewById<TextView>(R.id.tvEditActionSaveRule).setOnClickListener(this::onViewClick)
        view.findViewById<TextView>(R.id.consEditActionAppInfo)
            .setOnClickListener(this::onViewClick)
        cbEditActionForceFlag = view.findViewById(R.id.cbEditActionForceFlag)
        etEditActionClassName = view.findViewById(R.id.etEditActionClassName)
        etEditActionActionForText = view.findViewById(R.id.etEditActionActionForText)
        etEditActionActionForId = view.findViewById(R.id.etEditActionActionForId)
        etCreateActionAppPackageName = view.findViewById(R.id.etCreateActionAppPackageName)
        cbEditActionForceFlag.setOnClickListener(this::onViewClick)
    }

    fun onViewClick(view: View) {
        activity?.run {
            when (view.id) {
                R.id.tvEditActionSaveRule -> {
                    saveRule()
                }
                R.id.cbEditActionForceFlag -> {

                }
                R.id.consEditActionAppInfo -> {
                    RouterNav.goAppListPage(this)
                }
                R.id.tvEditActionReadButton -> {
                    //读取指定包名的最后一个页面
                }
            }
        }
    }

    /**
     * 保存当前页面规则
     */
    private fun saveRule() {
        if (TextUtils.isEmpty(etCreateActionAppPackageName.text?.trim())) {
            Toast.makeText(context, "没有包， 抓个毛线", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(etEditActionClassName.text?.trim())) {
            Toast.makeText(context, "没有页面， 抓个毛线", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(etEditActionActionForText.text.trim()) && TextUtils.isEmpty(etEditActionActionForId.text.trim())) {
            Toast.makeText(context, "Text， Id 至少选择一个", Toast.LENGTH_SHORT).show()
            return
        }
        action.packageName = etCreateActionAppPackageName.text?.trim().toString()
    }

}
