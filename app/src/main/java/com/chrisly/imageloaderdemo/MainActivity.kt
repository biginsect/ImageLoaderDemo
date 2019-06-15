package com.chrisly.imageloaderdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ListAdapter(this)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        val urlList = listOf(
            "https://stock.tuchong.com/?source=extbaidukey8&utm_source=extbaidukey8#378983035183038486",
            "https://stock.tuchong.com/?source=extbaidukey8&utm_source=extbaidukey8#261208855773708423",
            "https://stock.tuchong.com/?source=extbaidukey8&utm_source=extbaidukey8#238502033694654741",
            "https://stock.tuchong.com/?source=extbaidukey8&utm_source=extbaidukey8#235032121026281551",
            "https://stock.tuchong.com/?source=extbaidukey8&utm_source=extbaidukey8#539432748423577609",
            "https://stock.tuchong.com/?source=extbaidukey8&utm_source=extbaidukey8#450872799132385809",
            "https://stock.tuchong.com/?source=extbaidukey8&utm_source=extbaidukey8#261963842304868376")
        adapter.addData(urlList)
    }

}
