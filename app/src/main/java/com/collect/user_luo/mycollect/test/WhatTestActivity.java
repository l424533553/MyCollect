package com.collect.user_luo.mycollect.test;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.collect.user_luo.mycollect.R;

import leavesc.hello.databindingsamples.BlankFragment;

import static com.collect.user_luo.mycollect.config.IIntentConstants.INTENT_EXTRA_NAME;
import static com.collect.user_luo.mycollect.config.IIntentConstants.JUMP_VIEW2_TEST1;

public class WhatTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_test);
        String param = getIntent().getStringExtra(INTENT_EXTRA_NAME);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (JUMP_VIEW2_TEST1.equals(param)) {
            Test1Fragment test1Fragment = new Test1Fragment();
            fragmentTransaction.replace(R.id.fragment, test1Fragment, param);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
