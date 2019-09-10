package com.collect.user_luo.mycollect.activity.webview;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.collect.user_luo.R;
import com.collect.user_luo.databinding.ActivityWebMenuBinding;
import com.collect.user_luo.mycollect.adapter.StringAdapter;
import com.collect.user_luo.mycollect.data.HomeData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.collect.user_luo.mycollect.config.IConstants.EXTRA_INTENT;
import static com.collect.user_luo.mycollect.config.IConstants.EXTRA_INTENT_JUMP2_WEB_TEST1;

/**
 * 关于WebView 及Web 方向的测试使用
 */
public class WebMenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Map<String, Class> map;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWebMenuBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_web_menu);

        data = new ArrayList<>();
        map = HomeData.getWebMapData();
        if (map != null) {
            data.addAll(map.keySet());
        }
        StringAdapter adapter = new StringAdapter(this, data);
        binding.listView.setAdapter(adapter);
        binding.listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        Class classT = map.get(data.get(position));
        if (classT != null) {
            intent.setClass(this, classT);
            intent.putExtra(EXTRA_INTENT, EXTRA_INTENT_JUMP2_WEB_TEST1);
            startActivity(intent);
        }
    }

}
