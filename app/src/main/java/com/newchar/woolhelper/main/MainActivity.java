package com.newchar.woolhelper.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.newchar.woolhelper.R;
import com.newchar.woolhelper.adapter.ActionListAdapters;
import com.newchar.woolhelper.addcmd.AddCmdActivity;
import com.newchar.woolhelper.db.SQLHelper;
import com.newchar.woolhelper.entry.HasOrderAppItem;
import com.newchar.woolhelper.helper.EasyGlobalThread;

/**
 * @author newChar
 * date 2021/6/16
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class MainActivity extends Activity {

    private ImageButton mBtnMainCreateAction;
    private ListView mLvMainActionList;
    private ActionListAdapters mActionListAdapter;


    private static final int MSG_SEARCH_CMD = 1;
    private final Handler.Callback mSqlResult = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SEARCH_CMD:
                    searchSqlForHasCmdApp();
                    break;
            }
            return false;
        }
    };
    private final Handler mEasyThreadHandler = EasyGlobalThread.getEasyThreadHandler(mSqlResult);

    /**
     * 查询有脚本的App，在本地存储过sql数据的App
     */
    private void searchSqlForHasCmdApp() {
        SQLHelper sqlHelper = new SQLHelper(getApplicationContext());
        SQLiteDatabase readableDatabase = sqlHelper.getReadableDatabase();
        try (Cursor query = readableDatabase.query(HasOrderAppItem.TABLE.NAME, null, null, null, null, null, null)) {

        } catch (Exception ignored) {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 展开服务列表， 是否开启服务。
        mLvMainActionList = findViewById(R.id.lvMainActionList);
        mBtnMainCreateAction = findViewById(R.id.btnMainCreateAction);
        mActionListAdapter = new ActionListAdapters();
        mLvMainActionList.setAdapter(mActionListAdapter);

        mBtnMainCreateAction.setOnClickListener(mClickListener);
        mEasyThreadHandler.sendEmptyMessage(MSG_SEARCH_CMD);
    }

    private final View.OnClickListener mClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnMainCreateAction:
                    addCmd();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 添加命令，打开添加页面，选择App
     */
    private void addCmd() {
        AddCmdActivity.launch(this);
    }

    @Override
    protected void onDestroy() {
        mEasyThreadHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

}
