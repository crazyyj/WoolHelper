package com.newchar.woolhelper.base;


import android.view.View;

import java.util.List;

/**
 * ListView 的 ViewHolder 标准
 *
 * @author newChar
 */
public abstract class BaseAdapterViewHolder<T> implements View.OnClickListener {

    public abstract int getLayoutId();

    public abstract void initWidgets(View v);

    public abstract void setData(List<T> data, int position);

}

