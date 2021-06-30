package com.newchar.woolhelper.entry;

/**
 * @author newChar
 * date 2021/6/23
 * @since 配置过命令的App
 * @since 迭代版本，（以及描述）
 */
public class HasOrderAppItem {

    /**
     * 包名，根据包名取图标，以及App名称
     */
    private String appPackage;

    /**
     * 有几条命令
     */
    private int orderNumber;

    /**
     * 手动描述
     */
    private String describe;

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public class TABLE {

        public static final String NAME = "has_order_app_item";

    }

    public class COLUMNS {

        public static final String COLUMNS_APP_PACKAGE_NAME = "app_package";

        public static final String COLUMNS_ORDER_NUMBER = "order_number";

        public static final String COLUMNS_DESCRIBE = "describe";
    }



}
