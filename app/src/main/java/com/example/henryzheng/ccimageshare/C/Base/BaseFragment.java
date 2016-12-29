package com.example.henryzheng.ccimageshare.C.Base;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.henryzheng.ccimageshare.M.Interface.IHandlerListener;

import org.xutils.x;


/**
 * Created by henryzheng on 2016/9/27.
 */
public class BaseFragment extends Fragment implements IHandlerListener {

    private boolean injected = false;
    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            OnHandlerListener(msg);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        return x.view().inject(this, inflater, container);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
    }

    @Override
    public void OnHandlerListener(Message msg) {

    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler mHandler) {
        this.handler = mHandler;
    }

}
