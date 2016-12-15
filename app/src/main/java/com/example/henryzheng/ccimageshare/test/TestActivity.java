package com.example.henryzheng.ccimageshare.test;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.R;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_test)
public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
       ImageView image= (ImageView) findViewById(R.id.iv);
        image.setBackgroundResource(R.drawable.load);
        AnimationDrawable anim = (AnimationDrawable) image.getBackground();
        anim.start();
    }
}
