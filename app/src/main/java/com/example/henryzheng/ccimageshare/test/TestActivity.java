package com.example.henryzheng.ccimageshare.test;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.M.utils.CCLog;
import com.example.henryzheng.ccimageshare.M.utils.CCPictureUtil;
import com.example.henryzheng.ccimageshare.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_test)
public class TestActivity extends BaseActivity {
    private String mYear,mMonth,mDay;
    @ViewInject(R.id.iv0)
    private ImageView iv0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iv0.setDrawingCacheEnabled(true);

       // swipeRefreshLayout.setLayoutDirection();
    }
    @Event(value = R.id.btn0,type = View.OnClickListener.class)
    private void btnOnCLick(View view){
        CCLog.print( "bitcount before="+((BitmapDrawable)iv0.getDrawable()).getBitmap().getByteCount()+"");
        new Thread(new Runnable() {
            @Override
            public void run() {
               CCLog.print( "bitcount after="+CCPictureUtil.getGSBitmap(((BitmapDrawable)iv0.getDrawable()).getBitmap()).getByteCount()+"");
            }
        }).start();
        iv0.setImageBitmap(CCPictureUtil.getGSBitmap( ((BitmapDrawable)iv0.getDrawable()).getBitmap()));

    }
}
