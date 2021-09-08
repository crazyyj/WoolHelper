package com.newchar.woolhelper.applist;

import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.newchar.accesshelper.Utils;
import com.newchar.accesshelper.entry.AppInfo;
import com.newchar.woolhelper.R;
import com.newchar.woolhelper.base.BaseAdapterViewHolder;

import java.util.List;

/**
 * @author newChar
 * date 2021/9/2
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class AppListViewHolder extends BaseAdapterViewHolder<AppInfo> {

    private ImageView ivAppIcon;
    private TextView tvAppName;
    private TextView tvPackName;

    @Override
    public int getLayoutId() {
        return R.layout.item_adapter_app_list;
    }

    @Override
    public void initWidgets(View v) {
        tvAppName = v.findViewById(R.id.tvAppName);
        ivAppIcon = v.findViewById(R.id.ivAppIcon);
        tvPackName = v.findViewById(R.id.tvPackName);
    }

    @Override
    public void setData(List<AppInfo> data, int position) {
        AppInfo appInfo = data.get(position);
        tvAppName.setText(appInfo.appName);
        tvPackName.setText(appInfo.packageName);
        ivAppIcon.setImageDrawable(appInfo.icon);
    }

    @Override
    public void onClick(View v) {

    }

}

