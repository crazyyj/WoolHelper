package com.newchar.woolhelper.addcmd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.newchar.accesshelper.appinfo.AppInfoManager;
import com.newchar.accesshelper.entry.AppInfo;
import com.newchar.woolhelper.R;
import com.newchar.woolhelper.RouterNav;
import com.newchar.woolhelper.applist.AppListActivity;
import com.newchar.woolhelper.base.BaseActivity;

/**
 * @author newChar
 * date 2021/6/30
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class AddCmdActivity extends BaseActivity {

    private ImageView mIvAddCmdPackageName;
    private EditText etAddCmdPackageName;
    private AppInfo mCurrentSelectAppInfo;

    public static void launch(Context context) {
        Intent intent = new Intent(context, AddCmdActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        etAddCmdPackageName = findViewById(R.id.etAddCmdPackageName);
        mIvAddCmdPackageName = findViewById(R.id.ivAddCmdPackageName);

        etAddCmdPackageName.setOnClickListener(onClickListener);
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RouterNav.goAppListPage(AddCmdActivity.this);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == AppListActivity.REQUEST_CODE_APP_LIST) {
                String packageName = data.getStringExtra(AppListActivity.KEY_);
                mCurrentSelectAppInfo = AppInfoManager.get().searchAppForPackage(packageName);
                etAddCmdPackageName.setText(mCurrentSelectAppInfo.appName);
            }
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_add_cmd;
    }

}
