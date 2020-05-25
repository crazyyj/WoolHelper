package com.newchar.woolhelper.fragment

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
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
import com.newchar.woolhelper.entry.SelectorItem
import com.newchar.woolhelper.util.SQLUtils
import com.newchar.woolhelper.util.TextUtil

/**
 *  @author wenliqiang
 *  date 2019-12-01
 *  @since 读取数据库中的事件配置用来显示
 *  @since 迭代版本，（以及描述）
 */
class EditActionFragment : Fragment() {

    var id: String? = ""
    val action = ActionEntry()
    var selectorItem: SelectorItem? = null

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
                    //读取指定包名的最后一个页面， 如果Map中不存在，则读取启动页
                }
                R.id.etEditActionActionName -> {        //选择事件， 弹出弹窗
                    RouterNav.goSelectorItemPage(this)
                }
            }
        }
    }

    /**
     * 保存当前页面规则
     */
    private fun saveRule() {
        if (TextUtils.isEmpty(TextUtil.getTextWithTrim(etCreateActionAppPackageName, true))) {
            Toast.makeText(context, "没有包， 抓个毛线", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(TextUtil.getTextWithTrim(etEditActionClassName, true))) {
            Toast.makeText(context, "没有页面， 抓个毛线", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(TextUtil.getTextWithTrim(etEditActionActionForText, true))
            && TextUtils.isEmpty(TextUtil.getTextWithTrim(etEditActionActionForId, true))) {
            Toast.makeText(context, "Text， Id 至少选择一个", Toast.LENGTH_SHORT).show()
            return
        }
        action.packageName = TextUtil.getText(etCreateActionAppPackageName)

        activity?.let { SQLUtils.updateDBAction(it, action) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> {      //去了选择事件页面
                data?.run {
                     selectorItem = getParcelableExtra("RETURN_DATA") as SelectorItem
                }
            }
        }

    }

}
