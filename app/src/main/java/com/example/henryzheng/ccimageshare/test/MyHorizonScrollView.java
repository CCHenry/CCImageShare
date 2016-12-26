package com.example.henryzheng.ccimageshare.test;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by henryzheng on 2016/12/26.
 */
public class MyHorizonScrollView extends HorizontalScrollView{
    public MyHorizonScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public void fling(int velocityX) {
        super.fling(velocityX);
    }


}
