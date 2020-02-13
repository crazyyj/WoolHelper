package com.newchar.woolhelper.state;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author wenliqiang
 * date 2019-12-26
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class ViewAttach extends BaseStateLayout<View> {

    //1. copy LayoutParams;
    //2. 有多个子childView的时候 要直接填充覆盖布局大小, 不需要覆盖
    //3. copy 这个View 覆盖他
    private void copyParams(View copy) {
        final ViewGroup.LayoutParams copyParams = copy.getLayoutParams();
        //
        host.setLayoutParams(copyParams);
    }

}
