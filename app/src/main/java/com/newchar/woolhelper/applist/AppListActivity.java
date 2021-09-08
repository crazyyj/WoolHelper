package com.newchar.woolhelper.applist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.newchar.accesshelper.appinfo.AppInfoManager;
import com.newchar.accesshelper.entry.AppInfo;
import com.newchar.woolhelper.R;
import com.newchar.woolhelper.base.BaseActivity;

/**
 * @author newChar
 * date 2021/9/2
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class AppListActivity extends BaseActivity {

    private ListView mLvAppList;
    private AppListAdapter mAppListAdapter;

    public static final int REQUEST_CODE_APP_LIST = 20;
    public static final String KEY_ = "ABC";

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
        mLvAppList.setOnItemClickListener(this::onItemClick);
    }

    private void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra(KEY_, ((AppInfo) mAppListAdapter.getItem(position)).packageName);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onGlobalLayoutInitFinish() {
        super.onGlobalLayoutInitFinish();
        mAppListAdapter = new AppListAdapter();
        mLvAppList.setAdapter(mAppListAdapter);
        loadAllAppInfo();
    }

    /**
     * 耗时操作，提前初始化。
     */
    private void loadAllAppInfo() {
        mAppListAdapter.notifyDataSetChanged(AppInfoManager.get().getAllAppInfo());
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_app_list;
    }

}
