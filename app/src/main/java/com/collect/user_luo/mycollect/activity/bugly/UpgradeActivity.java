package com.collect.user_luo.mycollect.activity.bugly;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.collect.user_luo.mycollect.R;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.DownloadTask;

/**
 * 自定义Activity.
 */
public class UpgradeActivity extends Activity implements View.OnClickListener {
    private TextView tv;
    private Button start;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                Beta.cancelDownload();
                finish();
                break;
            case R.id.start:// 开始下载
                DownloadTask task = Beta.startDownload();
                updateBtn(task);
//                if (task.getStatus() == DownloadTask.DOWNLOADING) {
//                    finish();
//                }
                break;
        }

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_upgrade_bugly);
        tv = getView(R.id.tv);
        TextView title = getView(R.id.title);
        TextView version = getView(R.id.version);
        TextView size = getView(R.id.size);
        TextView time = getView(R.id.time);
        TextView content = getView(R.id.content);
        getView(R.id.cancel).setOnClickListener(this);
        getView(R.id.start).setOnClickListener(this);
        start = getView(R.id.start);


        /* *******获取下载任务，初始化界面信息*/
        updateBtn(Beta.getStrategyTask());


        tv.setText(tv.getText().toString() + String.valueOf(Beta.getStrategyTask().getSavedLength()));
        title.setText(title.getText().toString() + Beta.getUpgradeInfo().title);
        version.setText(version.getText().toString() + Beta.getUpgradeInfo().versionName);
        size.setText(size.getText().toString() + Beta.getUpgradeInfo().fileSize + "");
        time.setText(time.getText().toString() + Beta.getUpgradeInfo().publishTime + "");

        content.setText(Beta.getUpgradeInfo().newFeature);


        Beta.registerDownloadListener(new DownloadListener() {
            @Override
            public void onReceive(DownloadTask task) {
                updateBtn(task);
                tv.setText(task.getSavedLength() + "");
            }

            @Override
            public void onCompleted(DownloadTask task) {
                updateBtn(task);
                tv.setText(task.getSavedLength() + "");
            }

            @Override
            public void onFailed(DownloadTask task, int code, String extMsg) {
                updateBtn(task);
                tv.setText("failed");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Beta.unregisterDownloadListener();
    }


    public void updateBtn(DownloadTask task) {
        switch (task.getStatus()) {
            case DownloadTask.INIT:
            case DownloadTask.DELETED:
            case DownloadTask.FAILED: {
                start.setText("开始下载");
            }
            break;
            case DownloadTask.COMPLETE: {
                start.setText("安装");
            }
            break;
            case DownloadTask.DOWNLOADING: {
                start.setText("暂停");
            }
            break;
            case DownloadTask.PAUSED: {
                start.setText("继续下载");
            }
            break;
            default:
                break;
        }
    }

    public <T extends View> T getView(int id) {
        return  findViewById(id);
    }


}
