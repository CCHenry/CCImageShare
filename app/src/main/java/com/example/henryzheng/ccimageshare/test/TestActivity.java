package com.example.henryzheng.ccimageshare.test;

import android.os.Bundle;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestActivity extends BaseActivity {
    private String mYear,mMonth,mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final Calendar c = Calendar.getInstance();

       // swipeRefreshLayout.setLayoutDirection();
    }
}
