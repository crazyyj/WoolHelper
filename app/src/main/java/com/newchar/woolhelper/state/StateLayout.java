package com.newchar.woolhelper.state;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.collection.SparseArrayCompat;

import com.tongxue.tiku.base.R;
import com.tongxue.tiku.lib.util.Logger;

/**
 * @author wenliqiang@100tal.com
 * date            2019-12-21
 * @since 每一种状态布局的鸡肋
 * @since 迭代版本描述
 */
public class StateLayout {

    private SparseArrayCompat<View> viewContainer = new SparseArrayCompat<>();
    private View stateView;

    public void convert(View view) {

    }

    @LayoutRes
    public int getLayoutId() {
        return R.layout.common_view_empty;
    }

    public void setEventListener(@IdRes int viewId, View.OnClickListener clickListener) {
        View view = viewContainer.get(viewId);
        if (view == null) {
            viewContainer.put(viewId, stateView.findViewById(viewId));
        }
        View eventView = viewContainer.get(viewId);
        if (eventView == null) {
            Logger.e("StateLayout 不包含此 View 无法设置点击事件");
            return;
        }
        eventView.setOnClickListener(clickListener);
    }

}
