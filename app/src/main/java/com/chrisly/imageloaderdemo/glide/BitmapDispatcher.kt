package com.chrisly.imageloaderdemo.glide

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import java.io.InputStream
import java.net.URL
import java.util.concurrent.BlockingQueue

/**
 * 对BitmapRequest进行处理
 * @author big insect
 * @date 2019/5/31.
 */
class BitmapDispatcher(private val requestQueue: BlockingQueue<BitmapRequest>): Thread() {

    private var handler: Handler = Handler(Looper.getMainLooper())

    override fun run() {
        super.run()
        while (!isInterrupted){
            val request = requestQueue.take()
            setLoadingImage(request)
            val bitmap = findBitmap(request)
            show(request, bitmap)
        }
    }

    private fun setLoadingImage(bitmapRequest: BitmapRequest){
        if (bitmapRequest.resourceId > 0 && bitmapRequest.getImageView() != null){
            val resourceId = bitmapRequest.resourceId
            val imageView = bitmapRequest.getImageView()
            handler.post {
                imageView?.setImageResource(resourceId)
            }
        }
    }

    private fun findBitmap(bitmapRequest: BitmapRequest): Bitmap? {
        return downloadImage(bitmapRequest.url)
    }

    private fun show(bitmapRequest: BitmapRequest, bitmap: Bitmap?){
        val imageView = bitmapRequest.getImageView()
        if (bitmap != null && imageView != null && bitmapRequest.urlMD5 == imageView.tag){
            handler.post {
                imageView.setImageBitmap(bitmap)
                val listener = bitmapRequest.requestListener
                listener?.onSuccess(bitmap)
            }
        }
    }

    private fun downloadImage(uri: String?): Bitmap?{
        val input: InputStream
        val bitmap: Bitmap
        val url = URL(uri)

        val conn = url.openConnection()
        input = conn.getInputStream()
        bitmap = BitmapFactory.decodeStream(input)

        input.close()

        return bitmap
    }
}