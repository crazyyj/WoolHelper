package com.newchar.woolhelper.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.newchar.accesshelper.entry.HasOrderAppItem;
import com.newchar.accesshelper.MockUtils;
import com.newchar.accesshelper.log.LLL;
import com.newchar.woolhelper.R;
import com.newchar.woolhelper.adapter.AllAppAdapter;
import com.newchar.accesshelper.db.SQLHelper;
import com.newchar.woolhelper.addcmd.AddCmdActivity;
import com.newchar.woolhelper.base.BaseActivity;
import com.newchar.woolhelper.helper.EasyGlobalThread;
import com.newchar.woolhelper.view.TitleView;


/**
 * @author newChar
 * date 2021/6/16
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class MainActivity extends BaseActivity {

    private ListView mLvMainActionList;
    private CursorAdapter mAllAppAdapter;

    private static final int MSG_SEARCH_CMD = 1;

    private static final int MSG_SEARCH_CMD_RESULT = 100;

    private final Handler.Callback mSqlResult = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SEARCH_CMD:
                    Log.e("Act", "handleMessage" + System.currentTimeMillis());
                    searchSqlForHasCmdApp();
                    break;
                case MSG_SEARCH_CMD_RESULT:
                    if (msg.obj instanceof Cursor) {
                        updateSqlForHasCmdAppResult((Cursor) msg.obj);
                    }
                    break;
            }
            return false;
        }
    };
    private ImageButton mIvCommonTitleRightMore;
    private TitleView mCommonTitleBar;

    /**
     * 刷新列表UI
     *
     * @param queryCursor Cursor 数据
     */
    private void updateSqlForHasCmdAppResult(Cursor queryCursor) {
        if (mAllAppAdapter != null) {
            mAllAppAdapter.swapCursor(queryCursor);
        }
    }

    private final Handler mEasyThreadHandler = EasyGlobalThread.getEasyThreadHandler(mSqlResult);
    private final Handler mThreadResultHandler = new Handler(Looper.getMainLooper(), mSqlResult);

    /**
     * 查询有脚本的App，在本地存储过sql数据的App
     */
    private void searchSqlForHasCmdApp() {
        SQLHelper sqlHelper = new SQLHelper(getApplicationContext());
        SQLiteDatabase readableDatabase = sqlHelper.getReadableDatabase();
        readableDatabase.beginTransaction();
        Cursor queryCursor = readableDatabase.query(HasOrderAppItem.TABLE.NAME, null, null, null, null, null, null);
        Log.e("Act", "query end " + System.currentTimeMillis());
        mThreadResultHandler.obtainMessage(MSG_SEARCH_CMD_RESULT, queryCursor).sendToTarget();
        readableDatabase.setTransactionSuccessful();
        readableDatabase.endTransaction();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 展开服务列表， 是否开启服务。
        mLvMainActionList = findViewById(R.id.lvMainActionList);
        mCommonTitleBar = findViewById(R.id.common_title_bar);
        mCommonTitleBar.setTitleText("首页首页");
        ImageButton mBtnMainCreateAction = findViewById(R.id.btnMainCreateAction);
//        mIvCommonTitleRightMore = findViewById(R.id.ivCommonTitleRightMore);
        mAllAppAdapter = new AllAppAdapter(this, null);
        mLvMainActionList.setAdapter(mAllAppAdapter);
        mLvMainActionList.setEmptyView(null);

//        mIvCommonTitleRightMore.setOnClickListener(mClickListener);
        mBtnMainCreateAction.setOnClickListener(mClickListener);

        mEasyThreadHandler.sendEmptyMessage(MSG_SEARCH_CMD);
    }

    @Override
    protected void onGlobalLayoutInitFinish() {
        super.onGlobalLayoutInitFinish();

    }

    @Override
    protected void onResume() {
        LLL.e("Act", "onResume start" + System.currentTimeMillis());
        super.onResume();
        MockUtils.saveFalseCmdAppData(4);
    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    private final View.OnClickListener mClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnMainCreateAction:
                    addCmd();
                    break;
                case R.id.ivCommonTitleRightMore:
                    showGlobalConfigWindow();
                    break;
                default:
                    break;
            }
        }
    };

    private void showGlobalConfigWindow() {
        ViewGroup contentView = findViewById(Window.ID_ANDROID_CONTENT);
        View popWindowView = LayoutInflater.from(getApplicationContext()).inflate(
                R.layout.pop_global_config, contentView, false);
        PopupWindow window = new PopupWindow(popWindowView, ViewGroup.LayoutParams.WRAP_CONTENT, 400);
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        window.setOutsideTouchable(true);
        window.showAsDropDown((View) mIvCommonTitleRightMore.getParent(), 0, 0, Gravity.END);

    }

    /**
     * 添加命令，打开添加页面，选择App
     */
    private void addCmd() {
        AddCmdActivity.launch(this);
//        MockUtils.saveFalseCmdAppData(4);
//        mEasyThreadHandler.sendEmptyMessage(MSG_SEARCH_CMD);
    }

    @Override
    protected void releaseRes() {
        super.releaseRes();
        mEasyThreadHandler.removeCallbacksAndMessages(null);
        mThreadResultHandler.removeCallbacksAndMessages(null);
    }

}
