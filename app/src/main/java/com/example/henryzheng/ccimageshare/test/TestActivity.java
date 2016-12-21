package com.example.henryzheng.ccimageshare.test;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.R;

public class TestActivity extends BaseActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout0);
        swipeRefreshLayout.setRefreshing(true);
       // swipeRefreshLayout.setLayoutDirection();
    }
}
