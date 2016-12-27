package com.example.henryzheng.ccimageshare.V;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * bigImage测试
 * Created by henryzheng on 2016/10/9.
 */
public class MyViewPage2 extends ViewPager {
    private boolean noScroll = false;
    private float startY=0;

    public MyViewPage2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewPage2(Context context) {
        super(context);
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    private float startX = 0;

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                startX=ev.getX();
//                startY=ev.getY();
//            case MotionEvent.ACTION_MOVE:
//                int dX = (int) (ev.getX() - startX);
//                int dY = (int) (ev.getY() - startY);
//
//                if (Math.abs(dX) > Math.abs(dY)) {//左右滑动
//                    CCLog.print("左右滑动");
//                    CCLog.print(getParent().getClass().getName());
//                    return true;
//
//                } else {//上下滑动
//                    CCLog.print("上下滑动");
//
//                    return false;
//                }
//        }Z
        return super.onTouchEvent(ev );
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

//        if (ev.getAction()==MotionEvent.ACTION_DOWN) {
//            startX = (int) ev.getX();
//            startY = (int) ev.getY();
//        }
        if (noScroll)
            return false;
        else
            return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

}
