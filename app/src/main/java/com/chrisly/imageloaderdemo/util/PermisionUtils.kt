package com.chrisly.imageloaderdemo.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat

/**
 * 权限检查
 * @author big insect
 * @date 2019/5/31.
 */
object PermisionUtils {

    private val PERMISSION_STORAGE = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private const val REQUEST_CODE = 100

    /**
     * 检查读写权限
     * */
    fun checkStoragePermission(activity: Activity){
        val permission = ActivityCompat.checkSelfPermission(activity,
            "android.permission.WRITE_EXTERNAL_STORAGE")
        if (permission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                PERMISSION_STORAGE,
                REQUEST_CODE
            )
        }
    }
}