package com.example.henryzheng.ccimageshare.test;

import android.os.Bundle;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.R;

public class TestActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

       // swipeRefreshLayout.setLayoutDirection();
    }
}
