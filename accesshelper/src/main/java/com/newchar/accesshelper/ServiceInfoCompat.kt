package com.newchar.accesshelper

import android.accessibilityservice.AccessibilityServiceInfo

/**
 *  @author newChar
 *  date 2021/3/18
 *  @since AccessibilityServiceInfo 构建类
 *  @since 迭代版本，（以及描述）
 */
class ServiceInfoCompat {

    fun build(serverInfo: AccessibilityServiceInfo): Builder {
        return Builder(serverInfo)
    }

    class Builder(private val serverInfo: AccessibilityServiceInfo) {

//        init {
//            if (serverInfo == null)
//                serverInfo = AccessibilityServiceInfo()
//        }

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
            serverInfo.packageNames?.plus(packages)
            return this
        }

        fun removePackage(pkg: String): Builder {
            val index = serverInfo.packageNames?.indexOf(pkg) ?: -1
            if (index > -1) {
                serverInfo.packageNames?.drop(index)
            }
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