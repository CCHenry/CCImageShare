package com.example.henryzheng.ccimageshare.C.Base;

import android.app.Application;
import android.content.Context;

import com.tencent.tauth.Tencent;

import org.xutils.x;

/**
 * Created by henryzheng on 2016/9/27.
 */
public class MyApplication extends Application {
    public static Context _context;
    public static Tencent mTencent;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
//        x.Ext.setDebug(BuildConfig.);
        _context = getApplicationContext();

//        x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能
        mTencent = Tencent.createInstance("1105732414", this.getApplicationContext());

    }
}
