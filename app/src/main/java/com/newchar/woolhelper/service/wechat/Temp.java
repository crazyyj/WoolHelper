package com.newchar.woolhelper.service.wechat;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

import com.newchar.accesshelper.BaseAccess;

import org.jetbrains.annotations.NotNull;

/**
 * @author wenliqiang@100tal.com
 * date            2019-11-11
 * @since 当前版本描述，
 * @since 迭代版本描述
 */
public class Temp implements BaseAccess {

    @NotNull
    @Override
    public String getPackName() {
        return null;
    }

    @NotNull
    @Override
    public String getClassName() {
        return null;
    }

    @Override
    public boolean isShouldHandleEvent(@NotNull AccessibilityEvent event) {
        return false;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean handleEvent(@NotNull AccessibilityService service, @NotNull AccessibilityEvent event) {
        return false;
    }
}
