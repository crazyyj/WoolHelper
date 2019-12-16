package com.newchar.woolhelper.entry

import android.os.Parcel
import android.os.Parcelable

/**
 *  @author wenliqiang
 *  date 2019-12-01
 *  @since 事件的每一个实例（数据库表）
 *  @since 迭代版本，（以及描述）
 */
class ActionEntry() : Parcelable {

    /**
     * 表中的自增
     */
    var _id: Int = 0

    /**
     *  事件id，（事件唯一值， 一定有值）
     */
    var id: String = ""

    /**
     *  事件的名称
     */
    var name: String = ""

    /**
     *  事件的描述
     */
    var desc: String = ""

    /**
     *  事件触发的页面
     */
    var pageName: String = ""

    /**
     *  事件触发的包名
     */
    var packageName: String = ""

    /**
     *  操作的事件
     *  点击，长按，滑动等。。。
     */
    var action: Int = 0

    /**
     *  寻找的文本（锚点文本）
     */
    var anchorText: String = ""


    /**
     *  寻找的文本
     *  包名id/jdnfksdnkfj
     */
    var viewId: String = ""



    /**
     *  寻找事件的类型
     *  1. 文本
     *  2. ViewId
     *  3. 坐标（高级版功能）
     */
    var actionType: Int = 0b0

    /**
     *  事件状态
     *  0.  事件可用（默认）
     *  -1. 已删除，待完全删除
     *  -2. 本地完全删除
     */
    var enableState: Int = 0

    /**
     *  事件是否开启
     *  0. 未开启
     *  1. 已开启
     */
    var open: Int = 0

    constructor(parcel: Parcel) : this() {
        _id = parcel.readInt()
        id = parcel.readString()
        action = parcel.readInt()
        name = parcel.readString()
        desc = parcel.readString()
        pageName = parcel.readString()
        packageName = parcel.readString()
        enableState = parcel.readInt()
        actionType = parcel.readInt()
        open = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(_id)
        parcel.writeString(id)
        parcel.writeInt(action)
        parcel.writeString(name)
        parcel.writeString(desc)
        parcel.writeString(pageName)
        parcel.writeString(packageName)
        parcel.writeInt(enableState)
        parcel.writeInt(actionType)
        parcel.writeInt(open)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ActionEntry> {
        override fun createFromParcel(parcel: Parcel): ActionEntry {
            return ActionEntry(parcel)
        }

        override fun newArray(size: Int): Array<ActionEntry?> {
            return arrayOfNulls(size)
        }
    }


}