package com.newchar.accesshelper.log

import android.util.Log
import android.view.accessibility.AccessibilityNodeInfo
import java.lang.StringBuilder

/**
 *  @author         wenliqiang@100tal.com
 *  date            2020-01-08
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
public class PrintNodeTree {


    companion object {

        lateinit var builder: StringBuilder

        fun print(rootNode: AccessibilityNodeInfo) {
            builder = StringBuilder()
            iterator(rootNode, 0)
            println("iterator "+ builder.toString());
//            Log.d("iterator", builder.substring(builder.toString().length / 2).toString())
//            Log.d("iterator", "--------------")
//            Log.d("iterator", builder.substring(builder.toString().length / 2, builder.toString().length ).toString())
        }

        private fun getNode(node: AccessibilityNodeInfo, index: Int): AccessibilityNodeInfo? {
            var child: AccessibilityNodeInfo? = null
            try {
                child = node.getChild(index)
            } catch (e: IndexOutOfBoundsException) {
                Log.e("PrintNodeTree", " ${node.className} 这个Node没有第 $index 个node 你越界了")
            }
            return child
        }

        private fun iterator(nodeInfo: AccessibilityNodeInfo, _index: Int) {
            printNode(nodeInfo, _index)
            for (index in 0 until nodeInfo.childCount) {
                val node = getNode(nodeInfo, index)
                node?.let {
                    iterator(node, index)
                }
            }
        }

        private fun printNode(node: AccessibilityNodeInfo?, index: Int) {
            node?.let {
                for (indexx in 0..index) {
                    builder.append(" ")
                }
                builder.append("--node viewId ${node.viewIdResourceName}, className ${node.className}, packageName ${node.packageName} text ${node.text} \n")
            }
        }

    }


}