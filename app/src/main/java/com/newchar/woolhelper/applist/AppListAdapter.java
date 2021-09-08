package com.newchar.woolhelper.applist;

import com.newchar.accesshelper.entry.AppInfo;
import com.newchar.woolhelper.base.BaseAdapterViewHolder;
import com.newchar.woolhelper.base.BaseListAdapter;

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
