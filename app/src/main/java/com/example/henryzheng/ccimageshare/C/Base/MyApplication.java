package com.example.henryzheng.ccimageshare.C.Base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by henryzheng on 2016/9/27.
 */
public class MyApplication extends Application {
    public static Context _context;
    public static List<Activity> activitys;
//    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
//        x.Ext.setDebug(BuildConfig.);
        _context = getApplicationContext();
        activitys=new ArrayList<>();
//        x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能
//        refWatcher=  LeakCanary.install(this);
    }
//    public static RefWatcher getRefWatcher(Context context) {
//        MyApplication application = (MyApplication) context.getApplicationContext();
//        return application.refWatcher;
//    }
    public static void  addActivity(Activity activity){
        activitys.add(activity);
    }
}
