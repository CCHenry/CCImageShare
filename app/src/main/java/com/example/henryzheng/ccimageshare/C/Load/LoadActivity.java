package com.example.henryzheng.ccimageshare.C.Load;

import android.os.Bundle;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.C.Base.MyApplication;
import com.example.henryzheng.ccimageshare.R;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_load)
public class LoadActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.addActivity(this);
    }
}
