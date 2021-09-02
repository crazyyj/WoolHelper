package com.newchar.woolhelper.applist;

import com.newchar.woolhelper.base.BaseAdapterViewHolder;
import com.newchar.woolhelper.base.BaseListAdapter;
import com.newchar.woolhelper.bean.AppInfo;

/**
 * @author newChar
 * date 2021/9/2
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class AppListAdapter extends BaseListAdapter<AppInfo> {

    @Override
    protected BaseAdapterViewHolder<AppInfo> getBaseHolder() {
        return new AppListViewHolder();
    }


}
