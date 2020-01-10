package com.newchar.accesshelper.log

/**
 *  @author         wenliqiang@100tal.com
 *  date            2020-01-08
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
class LogNode {

    /**
     * 自己，类名
     */
    var className: String? = null

    /**
     * 当前ViewId
     */
    var packName: String? = null

    /**
     * 当前ViewId
     */
    var viewId: String? = null

    /**
     * 当前节点是位于父节点的第几个
     */
    var index: Int = 0

    /**
     * 自己的子节点
     */
    var nodes: ArrayList<LogNode> = ArrayList()

}