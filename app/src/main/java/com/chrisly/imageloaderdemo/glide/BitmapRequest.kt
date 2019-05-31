package com.chrisly.imageloaderdemo.glide

import android.content.Context
import android.widget.ImageView
import com.chrisly.imageloaderdemo.util.MD5Utils
import java.lang.ref.SoftReference

/**
 * @author big insect
 * @date 2019/5/31.
 */
class BitmapRequest(private val context: Context) {

    var url: String? = null

    private var imageView: SoftReference<ImageView>? = null

    var resourceId: Int = 0

    var requestListener: RequestListener? = null

    //将图片与ImageView 绑定
    var urlMD5: String = ""

    fun load(url: String) :BitmapRequest{
        this.url = url
        this.urlMD5 = MD5Utils.toMD5(url)
        return this
    }

    //占位图
    fun loading(resourceId: Int): BitmapRequest{
        this.resourceId = resourceId
        return this
    }

    fun listener(listener: RequestListener): BitmapRequest{
        this.requestListener = listener
        return this
    }

    fun into(imageView: ImageView){
        imageView.tag = this.urlMD5
        this.imageView = SoftReference(imageView)
        RequestManager.getInstance().addRequest(this)
    }

    fun getImageView(): ImageView?{
        return imageView?.get()
    }
}