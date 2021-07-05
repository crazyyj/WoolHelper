package com.newchar.woolhelper.entry;

import android.provider.BaseColumns;

/**
 * @author newChar
 * date 2021/6/23
 * @since 配置过命令的App
 * @since 迭代版本，（以及描述）
 */
public class HasOrderAppItem {

    private String appPackage;

    private int orderNumber;

    private String describe;

    /**
     * 包名，根据包名取图标，以及App名称
     */
    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    /**
     * 有几条命令
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * 手动描述
     */
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public static final class TABLE {

        public static final String NAME = "has_order_app_item";

        public static String createTableSql() {
            return "CREATE TABLE " + NAME
                    + " ( " + HasOrderAppItem.COLUMNS._ID + " integer(4)"
                    + ", " + HasOrderAppItem.COLUMNS.COLUMNS_APP_ICON + " integer"
                    + ", " + HasOrderAppItem.COLUMNS.COLUMNS_APP_PACKAGE_NAME + " varchar"
                    + ", " + HasOrderAppItem.COLUMNS.COLUMNS_ORDER_NUMBER + " varchar"
                    + ", " + HasOrderAppItem.COLUMNS.COLUMNS_DESCRIBE + " varchar "
                    + " )";
        }

    }

    public static class COLUMNS implements BaseColumns {

        public static final String COLUMNS_APP_PACKAGE_NAME = "app_package";

        public static final String COLUMNS_ORDER_NUMBER = "order_number";

        public static final String COLUMNS_APP_ICON = "app_icon";

        public static final String COLUMNS_DESCRIBE = "describe";

        public static String[] getAllColumns() {
            return new String[]{COLUMNS_APP_ICON, COLUMNS_APP_PACKAGE_NAME, COLUMNS_ORDER_NUMBER, COLUMNS_DESCRIBE};
        }

    }


}
