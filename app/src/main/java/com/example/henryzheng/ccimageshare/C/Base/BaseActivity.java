package com.example.henryzheng.ccimageshare.C.Base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.WindowManager;

import com.example.henryzheng.ccimageshare.M.Interface.IHandlerListener;
import com.example.henryzheng.ccimageshare.V.CCDialog;

import org.xutils.x;


/**
 * Created by henryzheng on 2016/9/27.
 */
public class BaseActivity extends FragmentActivity implements IHandlerListener {
    private Display mDisplay;
    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            OnHandlerListener(msg);
        }
    };
    public Context context;

    public CCDialog _cCdialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mDisplay = getWindowManager().getDefaultDisplay();
        context=this;
        _cCdialog = new CCDialog(this);
    }

    public int getWidth() {
        return mDisplay.getWidth();
    }

    public int getHight() {
        return mDisplay.getHeight();
    }

    @Override
    public void OnHandlerListener(Message object) {

    }
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler mHandler) {
        this.handler = mHandler;
    }

}
