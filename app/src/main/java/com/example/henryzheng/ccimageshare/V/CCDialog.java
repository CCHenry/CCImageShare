package com.example.henryzheng.ccimageshare.V;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.henryzheng.ccimageshare.C.Base.MyApplication;
import com.example.henryzheng.ccimageshare.R;

/**
 * Created by henryzheng on 2016/12/27.
 */
public class CCDialog  {
    ImageView load;
    AnimationDrawable mAnimate;
    Context context;
    View view;
    Dialog dialog;
    public CCDialog(Context activity) {
        this.context= MyApplication._context;

        view= LayoutInflater.from(activity).inflate(R.layout.cc_dialog,null);
        load= (ImageView) view.findViewById(R.id.load);
        mAnimate = (AnimationDrawable) load.getBackground();
        mAnimate.setOneShot(false);
        dialog=new Dialog(activity,R.style.loading_dialog);
        dialog.setContentView(view);
    }

    public void show() {
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        mAnimate.start();

    }
    public void hide() {
        dialog.dismiss();
        mAnimate.stop();

    }


}
