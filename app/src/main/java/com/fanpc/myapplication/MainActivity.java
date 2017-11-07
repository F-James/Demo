package com.fanpc.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import com.fanpc.myapplication.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initOption(MenuItem item) {

    }
}
