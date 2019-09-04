package com.collect.user_luo.mycollect.activity.webview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.collect.user_luo.mycollect.R;

import static com.collect.user_luo.mycollect.config.IConstants.EXTRA_INTENT;
import static com.collect.user_luo.mycollect.config.IConstants.EXTRA_INTENT_JUMP2_WEB_TEST1;

public class WebWhatActivity extends AppCompatActivity {


    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_what);
        String intentExtra = getIntent().getStringExtra(EXTRA_INTENT);

        if (EXTRA_INTENT_JUMP2_WEB_TEST1.equals(intentExtra)) {
            fragment = new WebTest1Fragment();
        }

        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment, fragment);
            transaction.commit();
        }

    }
}
