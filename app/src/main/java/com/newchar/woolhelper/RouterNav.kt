package com.newchar.woolhelper

import android.content.Context
import android.content.Intent
import com.newchar.woolhelper.fragment.ActionRecycleBinFragment

/**
 *  @author wenliqiang
 *  date 2019-12-01
 *  @since 当前版本，（以及描述）
 *  @since 迭代版本，（以及描述）
 */
class RouterNav {

    companion object{
        fun goAppListPage(ctx: Context) {
            val intent = Intent(ctx, MainActivity::class.java)
            ctx.startActivity(intent)
        }
    }

}