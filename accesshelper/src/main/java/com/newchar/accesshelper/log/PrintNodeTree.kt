package com.newchar.accesshelper.log

import android.view.accessibility.AccessibilityNodeInfo

/**
 *  @author         wenliqiang@100tal.com
 *  date            2020-01-08
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
class PrintNodeTree {


    companion object {
        lateinit var layerNumber: ArrayList<LogNode>

        fun print(rootNode: AccessibilityNodeInfo) {
            layerNumber = ArrayList()
            iterator(
                rootNode,
                0
            )
            printIt(
                layerNumber
            )
        }

        private fun printIt(layerNumber: ArrayList<LogNode>) {
            layerNumber.let {
                for (index in 0 until layerNumber.size) {
                    try {
                        val layer = layerNumber.get(index)
                        print("ViewId :${layer.viewId}, className :${layer.className}, packageName :${layer.packName}, index :${layer.index}")
//                        printIt(layer.nodes
                    } catch (e: IndexOutOfBoundsException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        private fun getNode(node: AccessibilityNodeInfo, index: Int): AccessibilityNodeInfo? {
            var child: AccessibilityNodeInfo? = null
            try {
                child = node.getChild(index)
            } catch (e: IndexOutOfBoundsException) {
//                Log.e("getNode 异常了", "className + ${node.className} index + $index")
            }
            return child
        }

        private fun iterator(nodeInfo: AccessibilityNodeInfo, _index: Int) {
            if (nodeInfo.childCount > 0) {
                val childNode = LogNode()
                for (index in 0..nodeInfo.childCount) {
                    val node =
                        getNode(
                            nodeInfo,
                            index
                        )
                    node?.let {
                        val childSubNode =
                            LogNode()
                        childSubNode.index = index
                        childSubNode.viewId = it.viewIdResourceName
                        childSubNode.className = it.className.toString()
                        childSubNode.packName = it.packageName.toString()
                        childNode.nodes.add(childSubNode)
                        iterator(
                            it,
                            index
                        )
                    }
                }
                layerNumber.add(childNode)
            } else {
                val singleNode = LogNode()
                singleNode.index = _index
                singleNode.viewId = nodeInfo.viewIdResourceName
                singleNode.className = nodeInfo.className.toString()
                singleNode.packName = nodeInfo.packageName.toString()
                layerNumber.add(singleNode)
            }
        }
    }


}