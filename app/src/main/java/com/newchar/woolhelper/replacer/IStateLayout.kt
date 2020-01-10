package com.newchar.woolhelper.replacer

/**
 * @author wenliqiang@100tal.com
 * date            2019-12-21
 * @since 当前版本描述，
 * @since 迭代版本描述
 */
interface IStateLayout<T> {
    fun attach(attach: T)
    val host: T
}