package com.newchar.accesshelper.compat

import android.accessibilityservice.AccessibilityServiceInfo
import android.view.accessibility.AccessibilityEvent

/**
 *  @author newChar
 *  date 2021/3/18
 *  @since AccessibilityServiceInfo 构建类
 *   <?xml version="1.0" encoding="utf-8"?>
 *   <accessibility-service xmlns:android="http://schemas.android.com/apk/res/android"
 *        android:accessibilityEventTypes="typeAllMask"
 *       android:accessibilityFeedbackType="feedbackSpoken"
 *        android:accessibilityFlags="flagRetrieveInteractiveWindows|flagRequestFilterKeyEvents"
 *        android:canRequestFilterKeyEvents="true"
 *        android:canRetrieveWindowContent="true"
 *       android:description="@string/app_name"
 *      android:notificationTimeout="100"
 *      android:packageNames="com.xxx.xxx" />
 *
 *  @since 迭代版本，（以及描述）
 */
class ServiceInfoCompat {

//    fun build(serverInfo: AccessibilityServiceInfo): Builder {
//        return Builder(serverInfo)
//    }

    class Builder(private val serverInfo: AccessibilityServiceInfo) {

        init {
//            serverInfo.packageNames = arrayOfNulls<String>(1)
        }

        fun addPackage(pkg: String): Builder {
            val size = serverInfo.packageNames?.size ?: 0
            if (size > 0) {
                serverInfo.packageNames?.plus(pkg)
            } else {
                serverInfo.packageNames = arrayOf(pkg)
            }
            return this
        }

        fun addPackages(packages: Array<String>): Builder {
            serverInfo.packageNames?.plus(packages)
            return this
        }

        fun addPackages(packages: Set<String>): Builder {
            val size = serverInfo.packageNames?.size ?: 0
            if (size > 0) {
                serverInfo.packageNames = serverInfo.packageNames.plus(packages)
            } else {
                serverInfo.packageNames = packages.toTypedArray()
            }
            return this
        }

        fun removePackage(pkg: String): Builder {
            val index = serverInfo.packageNames?.indexOf(pkg) ?: -1
            if (index > -1) {
                serverInfo.packageNames?.drop(index)
            }
            return this
        }

        fun eventType(): Builder {
            serverInfo.eventTypes = AccessibilityEvent.TYPES_ALL_MASK
            return this
        }

        fun feedBack(): Builder {
            serverInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK
            return this
        }
        fun timeOut(): Builder {
            serverInfo.notificationTimeout = 100L
            return this
        }

        fun des(): Builder {

            return this
        }

        fun capability(capability: Int): Builder {
            serverInfo.run {
                val mCapabilitiesField = this.javaClass.getDeclaredField("mCapabilities")
                mCapabilitiesField.isAccessible = true
                val capabilityOldValue = mCapabilitiesField.get(this) as Int
                mCapabilitiesField.set(this, capabilityOldValue.and(capability))
                mCapabilitiesField.isAccessible = false
            }
            return this
        }

        fun build(): AccessibilityServiceInfo {
            return serverInfo
        }

    }

}