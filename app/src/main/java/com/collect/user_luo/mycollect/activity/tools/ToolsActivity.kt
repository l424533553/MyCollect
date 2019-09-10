package com.collect.user_luo.mycollect.activity.tools

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.collect.user_luo.R
import com.collect.user_luo.mycollect.adapter.StringAdapter
import com.collect.user_luo.mycollect.data.HomeData

class ToolsActivity : Activity(), AdapterView.OnItemClickListener {


    var listView: ListView? = null
    var adapter: StringAdapter? = null


    var data222: List<String>? = null;
    var data: ArrayList<String>? = null


    internal var map: Map<String, Class<*>>? = null


    fun initView() {
        listView = findViewById(R.id.lvTools)

    }

    fun initData() {
        data = ArrayList()
        map = HomeData.getToolsMapData()
        if (map != null) {
            data!!.addAll(map!!.keys)
        }
        adapter = StringAdapter(this, data)
        listView?.adapter = adapter
        listView?.onItemClickListener = this
    }

    /**
     * 列表集合元素点击
     */
    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val intent = Intent()
        val className = data?.get(p2)
        // 数据
        val classT = map?.get(className) ?: return
        intent.setClass(this, classT::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tools)

        initView()
        initData()
    }

}
