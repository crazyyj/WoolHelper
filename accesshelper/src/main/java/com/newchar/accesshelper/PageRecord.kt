package com.newchar.accesshelper

import android.os.Parcel
import android.os.Parcelable
import android.view.accessibility.AccessibilityNodeInfo
import java.lang.ref.WeakReference

/**
 *  @author         wenliqiang@100tal.com
 *  date            2019-11-15
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
class PageRecord() : Parcelable {

    /**
     * 创建时间
     */
    var cTime: Long = 0L

    /**
     * 预计使用 ClassName
     */
    lateinit var uniqueKey: String

    /**
     * 根节点对象
     */
    lateinit var recordNodeInfo: WeakReference<AccessibilityNodeInfo>


    constructor(parcel: Parcel) : this() {
        cTime = parcel.readLong()
        uniqueKey = parcel.readString()
        recordNodeInfo = WeakReference(parcel.readParcelable(AccessibilityNodeInfo::class.java.classLoader))
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(cTime)
        parcel.writeString(uniqueKey)
        parcel.writeParcelable(recordNodeInfo.get(), flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PageRecord> {
        override fun createFromParcel(parcel: Parcel): PageRecord {
            return PageRecord(parcel)
        }

        override fun newArray(size: Int): Array<PageRecord?> {
            return arrayOfNulls(size)
        }
    }


}
