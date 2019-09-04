//package com.collect.user_luo.mycollect.activity.retrofit_rxjava
//
//import android.content.Intent
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.widget.AdapterView
//import android.widget.ListView
//import com.collect.user_luo.mycollect.R
//import com.collect.user_luo.mycollect.adapter.StringAdapter
//import com.collect.user_luo.mycollect.data.HomeData
//import java.util.ArrayList
//
//class RetrofitOrRxjavaActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
//
//    var listView: ListView? = null
//    var adapter: StringAdapter? = null
//
//    var data: ArrayList<String>? = null
//
//
//    internal var map: Map<String, Class<*>>? = null
//
//
//    fun initView() {
//        listView = findViewById(R.id.lvTools)
//    }
//
//    fun initData() {
//        data = ArrayList()
//        map = HomeData.getRetrofitMapData()
//        if (map != null) {
//            data!!.addAll(map!!.keys)
//        }
//        adapter = StringAdapter(this, data)
//        listView!!.adapter = adapter
//        listView!!.onItemClickListener = this
//
//    }
//
//    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//        val intent = Intent()
//        val classT = map!![data!![p2]]
//        intent.setClass(this, classT)
//        startActivity(intent)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_retrofit_or_rxjava)
//        initView()
//        initData()
//    }
//}
