package com.newchar.woolhelper.addcmd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.newchar.woolhelper.R;
import com.newchar.woolhelper.base.BaseActivity;

/**
 * @author newChar
 * date 2021/6/30
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class AddCmdActivity extends BaseActivity {

    private ListView mLvDevicesAppList;
    private ImageView mIvAddCmdPackageName;

    public static void launch(Context context) {
        Intent intent = new Intent(context, AddCmdActivity.class);
        context.startActivity(intent);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // 异步初始化布局，主线程 setContentView，在异步初始化View
//        setContentView(R.layout.activity_add_cmd);
//
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                mLvDevicesAppList = findViewById(R.id.lvDevicesAppList);
//                mIvAddCmdPackageName = findViewById(R.id.ivAddCmdPackageName);
//                mIvAddCmdPackageName.post(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                });
//            }
//        }.start();
//        getWindow().getDecorView().post(new Runnable() {
//            @Override
//            public void run() {
//
////                mIvAddCmdPackageName.setImageResource(R.drawable.common_ic_arrow_right);
//                Toast.makeText(AddCmdActivity.this, "asdf " + (mIvAddCmdPackageName == null), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    protected int getContentView() {
        return R.layout.activity_add_cmd;
    }

}
