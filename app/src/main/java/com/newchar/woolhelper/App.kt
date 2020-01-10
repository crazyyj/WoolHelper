package com.newchar.woolhelper

import android.app.Application
import android.content.Context

/**
 *  @author         wenliqiang@100tal.com
 *  date            2020-01-08
 *  @version
 *  @since          当前版本描述，
 *  @since          迭代版本描述
 */
class App : Application() {

    companion object{
        var context:Context? = null
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        context = base
    }
}