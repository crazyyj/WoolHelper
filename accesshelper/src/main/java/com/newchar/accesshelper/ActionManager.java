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

    private final static ActionManager instance = new ActionManager();

    public static ActionManager getInstance() {
        return instance;
    }

    /**
     * 添加一个动作
     */
    public void addAction(ActionWrapper action) {
        List<ActionWrapper> pkgNameValue = actionMap.get(action.pageName);
        if (pkgNameValue == null) {
            ArrayList<ActionWrapper> actionWrappers = new ArrayList<>();
            actionWrappers.add(action);
            actionMap.put(action.pageName, actionWrappers);
        } else {
            pkgNameValue.add(action);
        }
    }

    public void removeActionFromPkg(String pkg) {
        actionMap.remove(pkg);
    }

    public void loadActions(String json) {
        try {
            JSONObject actionJson = new JSONObject(json);
            String pkgName = actionJson.getString("pkgName");
            ActionWrapper actionW = new ActionWrapper();
            actionW.pageName = pkgName;
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
            actionW.actions.addAll(actionList);

            // 把这个action 添加进去
            addAction(actionW);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<String> getPackageNameIfEnable() {
        return actionMap.keySet();
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    public boolean searchPageMatchForEvent(AccessibilityEvent event) {
        Set<String> packageNameIfEnable = getPackageNameIfEnable();
        CharSequence packageName = event.getPackageName();
        if (packageNameIfEnable.contains(packageName)) {
            List<ActionWrapper> actionList = actionMap.get(packageName);
            if (actionList != null) {
                int size = actionList.size();
                for (int i = 0; i < size; i++) {
                    ActionWrapper actionWrapper = actionList.get(i);
                    if (TextUtils.equals(actionWrapper.pageName, event.getClassName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    public void execute(AccessibilityService service, AccessibilityEvent event) {
        if (service == null || event == null) {
            return;
        }

        List<ActionWrapper> actionWrappers = actionMap.get(event.getPackageName());
        if (actionWrappers == null) {
            return;
        }

        AccessibilityNodeInfo rootNodeInfo = service.getRootInActiveWindow();
        for (ActionWrapper actionWrapper : actionWrappers) {
            List<Action> actions = actionWrapper.actions;
            for (Action action : actions) {
                switch (action.actionSignType) {
                    case Action.ActionSign.BY_POINT:

                        break;
                    case Action.ActionSign.BY_TEXT:
                        List<AccessibilityNodeInfo> nodeInfoList = NodeInfoCompat.findNodeByText(rootNodeInfo, action.actionViewText);
                        AccessibilityNodeInfo node = nodeInfoList.get(0);
                        if (TextUtils.equals(action.action, Action.ActionEvent.CLICK)) {
                            ActionInfoCompat.performClick(node);
                        } else if (TextUtils.equals(Action.ActionEvent.CLICK_LONG, action.action)) {
                            ActionInfoCompat.performLongClick(node);
                        }
                        break;
                    case Action.ActionSign.BY_VIEW_ID:
                        if (!TextUtils.isEmpty(action.actionViewId)) {
                            List<AccessibilityNodeInfo> nodeInfoByViewId = NodeInfoCompat.findNodeByViewId(rootNodeInfo, action.actionViewId);
                            if (nodeInfoByViewId != null && !nodeInfoByViewId.isEmpty()) {
                                AccessibilityNodeInfo nodeById = nodeInfoByViewId.get(0);
                                if (nodeById != null) {
                                    if (TextUtils.equals(action.action, Action.ActionEvent.CLICK)) {
                                        ActionInfoCompat.performClick(nodeById);
                                    } else if (TextUtils.equals(action.action, Action.ActionEvent.CLICK_LONG)) {
                                        ActionInfoCompat.performLongClick(nodeById);
                                    } else if (TextUtils.equals(action.action, Action.ActionEvent.SCROLL_X_DOWN)) {
                                        ActionInfoCompat.scrollDown(nodeById);
                                    } else if (TextUtils.equals(action.action, Action.ActionEvent.SCROLL_Y_DOWN)) {
                                        ActionInfoCompat.scrollUp(nodeById);
                                    }
                                }
                            }
                        }
                        break;
                    default:
                        Log.e("Action", " 现在的actionSignType " + action.actionSignType);
                        break;
                }
            }
        }

    }


}