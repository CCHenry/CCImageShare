package com.example.henryzheng.ccimageshare.C.loginAndRegister;

import android.content.Intent;
import android.os.Bundle;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.C.main.MainPageActivity;
import com.example.henryzheng.ccimageshare.C.Base.MyApplication;
import com.example.henryzheng.ccimageshare.R;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_load)
public class LoadActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    if (MyApplication.mbmobUser.getUsername() == null) {
                        startActivity(new Intent(LoadActivity.this, LoginActivity.class));
                    } else {
                        startActivity(new Intent(LoadActivity.this, MainPageActivity.class));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
