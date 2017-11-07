package com.fanpc.myapplication.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        init(savedInstanceState);
    }

    public abstract int setLayoutId();

    protected abstract void init(Bundle savedInstanceState);


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        initOption(item);

        return super.onOptionsItemSelected(item);
    }

    protected abstract void initOption(MenuItem item);

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
