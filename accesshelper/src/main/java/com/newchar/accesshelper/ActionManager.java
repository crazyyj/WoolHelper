package com.newchar.accesshelper;

import android.accessibilityservice.AccessibilityService;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.newchar.accesshelper.compat.ActionInfoCompat;
import com.newchar.accesshelper.compat.NodeInfoCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author newChar
 * date 2021/3/29
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
class ActionManager {

    /**
     * 被管理的全部动作
     */
    private final Map<String, List<ActionWrapper>> actionMap = new HashMap<>();

    /**
     * 保存下来的动作
     */
    private ActionWrapper mWrapper;

    private final static ActionManager instance = new ActionManager();

    public static ActionManager getInstance() {
        return instance;
    }

    /**
     * 添加一个动作
     */
    public void addAction(String pkg, ActionWrapper action) {
        List<ActionWrapper> pkgNameValue = actionMap.get(action.getPageName());
        if (pkgNameValue == null) {
            ArrayList<ActionWrapper> actionWrappers = new ArrayList<>();
            actionWrappers.add(action);
            actionMap.put(pkg, actionWrappers);
        } else {
            pkgNameValue.add(action);
        }
    }

    public void removeAllActionFromPkg(String pkg) {
        actionMap.remove(pkg);
    }

    public void loadActions(String json) {
        try {
            JSONObject actionJson = new JSONObject(json);
            ActionWrapper actionW = new ActionWrapper();
            actionW.setPkgName(actionJson.getString("pkgName"));
            JSONArray actionJsonArray = actionJson.getJSONArray("actionList");
            List<Action> actionList = new ArrayList<>();
            for (int i = 0; i < actionJsonArray.length(); i++) {
                try {
                    JSONObject actionObj = (JSONObject) actionJsonArray.get(i);
                    Action action = Action.valueOf(actionObj);
                    if (null != action) {
                        actionList.add(action);
                    }
                } catch (Exception ignored) {
                }
            }
            actionW.getActions().addAll(actionList);

            // 把这个action 添加进去
            addAction(actionW.getPkgName(), actionW);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<String> getPackageNameIfEnable() {
        return actionMap.keySet();
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    boolean searchActionMatchForEvent(AccessibilityEvent event) {
        Set<String> packageNameIfEnable = getPackageNameIfEnable();
        CharSequence packageName = event.getPackageName();
        if (packageNameIfEnable.contains(packageName)) {
            List<ActionWrapper> actionList = actionMap.get(packageName);
            if (actionList != null) {
                int size = actionList.size();
                for (int i = 0; i < size; i++) {
                    ActionWrapper actionWrapper = actionList.get(i);
                    List<Action> actions = actionWrapper.getActions();
                    if (actions != null && !actions.isEmpty()) {
                        Action action = actions.get(0);
                        if (TextUtils.equals(action.actionPage, event.getClassName())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    int execute(AccessibilityService service, AccessibilityEvent event) {
        if (service == null || event == null) {
            return -1;
        }

        List<ActionWrapper> actionWrappers = actionMap.get(event.getPackageName());
        if (actionWrappers == null || actionWrappers.isEmpty()) {
            // 没有对应的组数据
            return -2;
        }

        AccessibilityNodeInfo rootNodeInfo = service.getRootInActiveWindow();
        if (rootNodeInfo == null) {
            Log.e("Server", "RootInActiveWindow 为 null无法访问后续流程");
            return -3;
        }
        for (int i = 0, size = actionWrappers.size(); i < size; i++) {
            ActionWrapper wrapper = actionWrappers.get(i);
            if (wrapper.isAvailable()) {
                mWrapper = wrapper;
                break;
            } else {
                mWrapper = null;
            }
        }
        if (mWrapper == null) {
            return -5;
        }
        Action action = filterList(mWrapper);
        if (action == null) {
            return -4;
        }
        if (!TextUtils.isEmpty(action.actionViewId)) {
            List<AccessibilityNodeInfo> nodeInfoByViewId = NodeInfoCompat.findNodeByViewId(rootNodeInfo, action.actionViewId);
            if (nodeInfoByViewId != null && !nodeInfoByViewId.isEmpty()) {
                AccessibilityNodeInfo nodeById = nodeInfoByViewId.get(0);
                performAction(nodeById, action.action);
            }
        }
        return 0;
    }

    /**
     * 过滤第一个可用的事件
     *
     * @param allActionInfo 事件包装类，处理同包事件
     * @return 当前第一个可用的事件
     */
    private Action filterList(ActionWrapper allActionInfo) {
        List<Action> actions = allActionInfo.getActions();
        for (Action action : actions) {
            if (action.state >= Action.State.ABLE) {
                action.state = Action.State.UNABLE_CURRENT_FLOW;
                return action;
            }
        }
        return null;
    }

    private boolean performAction(AccessibilityNodeInfo node, String action) {
        boolean performResult = false;
        if (node != null) {
            if (TextUtils.equals(action, Action.ActionEvent.CLICK)) {
                performResult = ActionInfoCompat.performClick(node);
            } else if (TextUtils.equals(action, Action.ActionEvent.CLICK_LONG)) {
                performResult = ActionInfoCompat.performLongClick(node);
            } else if (TextUtils.equals(action, Action.ActionEvent.SCROLL_X_DOWN)) {
                performResult = ActionInfoCompat.scrollDown(node);
            } else if (TextUtils.equals(action, Action.ActionEvent.SCROLL_Y_DOWN)) {
                performResult = ActionInfoCompat.scrollUp(node);
            } else if (TextUtils.equals(action, Action.ActionEvent.SELECT)) {
                performResult = ActionInfoCompat.performSelect(node);
            }
        }
        return performResult;
    }

}
