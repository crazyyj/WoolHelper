package com.newchar.woolhelper.applist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.newchar.accesshelper.log.LLL;
import com.newchar.woolhelper.R;
import com.newchar.woolhelper.base.BaseActivity;
import com.newchar.woolhelper.util.PhoneUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author newChar
 * date 2021/9/2
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class AppListActivity extends BaseActivity {

    private ListView mLvAppList;
    private BaseAdapter mAppListAdapter;

    public static final int REQUEST_CODE_APP_LIST = 20;

    public static void launch(Activity context) {
        Intent intent = new Intent(context, AppListActivity.class);
        context.startActivityForResult(intent, REQUEST_CODE_APP_LIST);
    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        mLvAppList = findViewById(R.id.lvAppList);

    }

    @Override
    protected void onGlobalLayoutInitFinish() {
        super.onGlobalLayoutInitFinish();
        mAppListAdapter = new AppListAdapter();
        mLvAppList.setAdapter(mAppListAdapter);

        loadAllAppInfo();
    }

    private void loadAllAppInfo() {
        List<PackageInfo> devicesAllAppInfo = PhoneUtils.getDevicesAllAppInfo(getApplicationContext());
        for (PackageInfo packageInfo : devicesAllAppInfo) {
//            LLL.w("AppInfo", Arrays.toString(packageInfo.activities));
            LLL.w("AppInfo", "" + packageInfo.applicationInfo.icon);
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_app_list;
    }

}
