package com.newchar.accesshelper.log;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * @author newChar
 * date 2021/3/29
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class PrintNodeTree {

    public static StringBuilder builder;

    public PrintNodeTree() {
        builder = new StringBuilder();
    }

    public static void print(AccessibilityNodeInfo rootNode) {
        iterator(rootNode, 0);
//        println("iterator "+ builder.toString());
    }

    private static void iterator(AccessibilityNodeInfo nodeInfo, int _index) {
        printNode(nodeInfo, _index);
        int childCount = nodeInfo.getChildCount();
        for (int i = 0; i < childCount; i++) {
            AccessibilityNodeInfo node = getNode(nodeInfo, i);
            if (node != null) {
                iterator(node, i);
            }
        }
    }


    private static AccessibilityNodeInfo getNode(AccessibilityNodeInfo node, int index) {
        AccessibilityNodeInfo child = null;
        try {
            child = node.getChild(index);
        } catch (IndexOutOfBoundsException e) {
            Log.e("PrintNodeTree", " ${node.className} 这个Node没有第 $index 个node 你越界了");
        }
        return child;
    }


    private static void printNode(AccessibilityNodeInfo node, int index) {
        if (node != null) {
            for (int i = 0; i < index; i++) {
                builder.append(" ");
            }
            builder.append("--node viewId ${node.viewIdResourceName}, className ${node.className}, packageName ${node.packageName} text ${node.text} \n");
        }
    }

}
