package com.newchar.woolhelper.state;

/**
 * @author wenliqiang@100tal.com
 * date            2019-12-21
 * @since 当前版本描述，
 * @since 迭代版本描述
 */
public interface IStateLayout<T> {

   void attach(T attach);

   T getHost();

}
