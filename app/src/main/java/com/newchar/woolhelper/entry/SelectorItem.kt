package com.newchar.woolhelper.entry

import android.os.Parcel
import android.os.Parcelable

/**
 *  @author wenliqiang
 *  date 2019-12-15
 *  @since 当前版本，（以及描述）
 *  @since 迭代版本，（以及描述）
 */
class SelectorItem() : Parcelable{

    /**
     *  选中显示前方箭头
     */
    var isSelector: Boolean = false

    /**
     *  显示的名称
     */
    var eventName: String = ""


    constructor(parcel: Parcel) : this() {
        isSelector = parcel.readByte() != 0.toByte()
        eventName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isSelector) 1 else 0)
        parcel.writeString(eventName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SelectorItem> {
        override fun createFromParcel(parcel: Parcel): SelectorItem {
            return SelectorItem(parcel)
        }

        override fun newArray(size: Int): Array<SelectorItem?> {
            return arrayOfNulls(size)
        }
    }

}