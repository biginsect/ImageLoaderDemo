package com.chrisly.imageloaderdemo.glide

import android.content.Context

/**
 * 对外开放接口
 * @author big insect
 * @date 2019/6/1.
 */
class SimpleGlide {

    fun with(context: Context): BitmapRequest{
        return BitmapRequest(context)
    }
}