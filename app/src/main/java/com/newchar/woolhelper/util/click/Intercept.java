package com.newchar.woolhelper.util.click;

import android.view.View;

/**
 * @author wenliqiang wenliqiang@100tal.com
 * date            2019-06-21
 * @since 当前版本描述，
 * @since 迭代版本描述
 */
public interface Intercept {

    /**
     * 默认函数内拦截
     */
    boolean onDefaultIntercept(View target);

    /**
     * 设置是否启用默认拦截 （默认 true）
     *
     * @param tag    针对这个tag 去标记 这个默认拦截是否生效。
     * @param enable 0 = 不生效，
     */
    void setDefaultInterceptEnable(View tag, int enable);

    /**
     * 用户拦截
     */
    boolean onUserIntercept(View view);

    /**
     * 销毁拦截器内资源
     */
    void destroy();

}
