package com.newchar.accesshelper;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author newChar
 * date 2021/3/29
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class BaseAccessibilityService extends AccessibilityService {

    private AccessManager accessManager;

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Logger.getLogger("AccessibilityService").log(Level.WARNING, "start");
        accessManager = new AccessManager(getApplicationContext());
        accessManager.setServiceInfoChangeListener(new AccessManager.ServiceInfoChangeListener() {
            @Override
            public void onServiceInfoChange(AccessibilityServiceInfo info) {
                setServiceInfo(info);
            }
        });
        accessManager.init();

    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event != null) {
//            把Event传进去，根据包名，类名过滤然后执行后边的具体判断罗技。要执行哪些操作
            accessManager.onAccessibilityEvent(this, event);
        }
    }

    @Override
    public void onInterrupt() {
        if (accessManager != null) {
            accessManager.release();
            accessManager = null;
        }

    }

}
