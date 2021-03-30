package com.newchar.accesshelper.log;

import java.util.ArrayList;

/**
 * @author newChar
 * date 2021/3/27
 * @since 本地打印树结构
 * @since 迭代版本，（以及描述）
 */
class LogNode {

    /**
     * 自己，类名
     */
    String className;

    /**
     * 当前ViewId
     */
    String packageName;

    /**
     * 当前ViewId
     */
    String viewID;

    /**
     * 当前节点是位于父节点的第几个
     */
    int index;

    /**
     * 自己的子节点
     */
    ArrayList<LogNode> nodes;

}
