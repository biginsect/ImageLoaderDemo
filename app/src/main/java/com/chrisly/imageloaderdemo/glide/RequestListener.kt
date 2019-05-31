package com.chrisly.imageloaderdemo.glide

import android.graphics.Bitmap

/**
 * 请求回调，需要对图片进行处理时可以设置
 * @author big insect
 * @date 2019/5/31.
 */
interface RequestListener {

    fun onSuccess(bitmap: Bitmap): Boolean

    fun onFailed()
}