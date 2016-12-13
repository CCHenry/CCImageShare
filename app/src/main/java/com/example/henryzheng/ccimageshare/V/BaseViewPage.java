package com.example.henryzheng.ccimageshare.V;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by henryzheng on 2016/10/9.
 */
public class BaseViewPage extends ViewPager {
    private boolean noScroll = false;
    private int startY = 0;

    public BaseViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseViewPage(Context context) {
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
    public boolean onTouchEvent(MotionEvent arg0) {
        if (noScroll)
            return false;
        else
            return super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {



//        if ((ev.getX() - startX) > 0 && getCurrentItem() == 1) {
//            CCLog.print("父view enter test>>>>>>>>>>>>>> startx=" + startX + ",now arg0.getX()=" + ev.getX());
//
//            return false;
//        }

            return super.onInterceptTouchEvent(ev);


    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

}
