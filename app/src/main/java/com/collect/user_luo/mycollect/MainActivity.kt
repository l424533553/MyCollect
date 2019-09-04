package com.collect.user_luo.mycollect

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.collect.user_luo.mycollect.utils.Mylog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.btnTest).setOnClickListener {

        }

        findViewById<View>(R.id.btnTest).setOnClickListener {
            Mylog.log("测试")
            it.isClickable=false
        }
    }
}
