package com.collect.user_luo.mycollect;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.collect.user_luo.R;
import com.collect.user_luo.mycollect.adapter.StringAdapter;
import com.collect.user_luo.mycollect.data.HomeData;
import com.collect.user_luo.mycollect.dialog.CardPickerDialog;
import com.xuanyuan.library.utils.animation.SnackAnimationUtil;
import com.xuanyuan.library.base.activity.MyAppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class HomeActivity extends MyAppCompatActivity implements AdapterView.OnItemClickListener, CardPickerDialog.ClickListener {
//    @Override
//    public boolean isCheckPermissions() {
//        return true;
//    }


    private ListView listView;
    private List<String> data;
    Map<String, Class> map;

    private void initView() {
        listView = findViewById(R.id.listView);

    }

    private void initData() {
        data = new ArrayList<>();
        map = HomeData.getHomeMapData();
        if (map != null) {
            data.addAll(map.keySet());
        }
        StringAdapter adapter = new StringAdapter(this, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();

        // 数据功能功能  开发测试
        // 测试消息栏通知的 帮助类
//        NotificationHelpter notificationHelpter = new NotificationHelpter(this);
//        notificationHelpter.createNotification(this, "test_id");

    }

    @Override
    protected String[] getPermissionsArray() {
        String[] arr = new String[]{
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.VIBRATE,
                Manifest.permission.CAMERA
        };
        return arr;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.change_theme) {
            CardPickerDialog dialog = new CardPickerDialog();
            dialog.setClickListener(this);
            dialog.show(getSupportFragmentManager(), CardPickerDialog.TAG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //    数据 数据功能  开发使用者  数据回修
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        Class classT = map.get(data.get(position));
        if (classT != null) {
            intent.setClass(this, classT);
            startActivity(intent);
        }
    }

    @Override
    public void onConfirm(int currentTheme) {
        if (ThemeHelper.getTheme(context) != currentTheme) {
            ThemeHelper.setTheme(context, currentTheme);
            ThemeUtils.refreshUI(context, new ThemeUtils.ExtraRefreshable() {
                        @Override
                        public void refreshGlobal(Activity activity) {
                            //for global setting, just do once
                            if (Build.VERSION.SDK_INT >= 21) {
                                ActivityManager.TaskDescription taskDescription =
                                        new ActivityManager.TaskDescription(null, null,
                                                ThemeUtils.getThemeAttrColor(context, android.R.attr.colorPrimary));
                                setTaskDescription(taskDescription);
                                getWindow().setStatusBarColor(ThemeUtils.getColorById(context, R.color.theme_color_primary_dark));
                            }
                        }

                        @Override
                        public void refreshSpecificView(View view) {
                            //TODO: will do this for each traversal
                        }
                    }
            );
            View view = findViewById(R.id.snack_layout);
            if (view != null) {
                TextView textView = view.findViewById(R.id.content);
                textView.setText(getSnackContent(currentTheme));
                SnackAnimationUtil.with(this, R.anim.snack_in, R.anim.snack_out)
                        .setDismissDelayTime(1000)
                        .setTarget(view)
                        .play();
            }
        }
    }

    /**
     * 获取弹出信息 内容
     *
     * @param current 当前的theme id
     */
    private String getSnackContent(int current) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        return getResources().getString(getResources().getIdentifier("magicasrkura_prompt_" + random.nextInt(3), "string", getPackageName())) + ThemeHelper.getName(current);
    }
}
