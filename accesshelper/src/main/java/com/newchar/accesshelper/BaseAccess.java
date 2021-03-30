package com.newchar.accesshelper;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

/**
 * @author newChar
 * date 2021/3/29
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public interface BaseAccess {

    String getPackName();

    String getClassName();

    boolean isShouldHandleEvent(AccessibilityEvent event);

    int getPriority();

    boolean handleEvent(AccessibilityService service, AccessibilityEvent event);

}
