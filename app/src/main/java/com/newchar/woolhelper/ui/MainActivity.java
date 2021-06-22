package com.newchar.woolhelper.ui;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageButton;
import android.widget.ListView;

import com.newchar.woolhelper.R;
import com.newchar.woolhelper.adapter.ActionListAdapters;
import com.newchar.woolhelper.db.SQLHelper;
import com.newchar.woolhelper.helper.EasyGlobalThread;

/**
 * @author newChar
 * date 2021/6/16
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class MainActivity extends Activity {

    private ImageButton btnMainCreateAction;
    private ListView lvMainActionList;
    private ActionListAdapters mActionListAdapter;

    private final Handler.Callback sqlResult = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    searchSqlForHasCmd();
                    break;
            }
            return false;
        }
    };
    Handler easyThreadHandler = EasyGlobalThread.getEasyThreadHandler(sqlResult);

    /**
     * 查询有脚本的App，在本地存储过sql数据的App
     */
    private void searchSqlForHasCmd() {
        SQLHelper sqlHelper = new SQLHelper(getApplicationContext());
        SQLiteDatabase readableDatabase = sqlHelper.getReadableDatabase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 展开服务列表， 是否开启服务。
        lvMainActionList = findViewById(R.id.lvMainActionList);
        btnMainCreateAction = findViewById(R.id.btnMainCreateAction);
        mActionListAdapter = new ActionListAdapters();
        lvMainActionList.setAdapter(mActionListAdapter);

        easyThreadHandler.sendEmptyMessage(1);
    }

    @Override
    protected void onDestroy() {
        easyThreadHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

}
