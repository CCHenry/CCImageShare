package com.example.henryzheng.ccimageshare.test;

import android.os.Bundle;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.R;
import com.example.henryzheng.ccimageshare.V.NavigationView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_test)
public class TestActivity extends BaseActivity {
    @ViewInject(R.id.vl)
    private NavigationView nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
