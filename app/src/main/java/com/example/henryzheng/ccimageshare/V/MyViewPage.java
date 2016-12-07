package com.example.henryzheng.ccimageshare.V;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.henryzheng.ccimageshare.M.common.CCLog;

/**
 * Created by henryzheng on 2016/10/9.
 */
public class MyViewPage extends ViewPager {
    private boolean noScroll = false;
    private int startY = 0;

    public MyViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewPage(Context context) {
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
        CCLog.print("父viewpage：enter onTouchEvent");
        if (noScroll)
            return false;
        else
            return super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (noScroll)
                return false;
//        if (mMainFragment.getCurrentItem() == 0&& getCurrentItem() == 1) {
//            switch (ev.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    startX = (int) ev.getX();
//                    startY = (int) ev.getY();
//                    break;
//                case MotionEvent.ACTION_MOVE:
//
//                    int dX = (int) (ev.getX() - startX);
//                    int dY = (int) (ev.getY() - startY);
////                CCLog.print("enter test>>>>>>>>>>>>>> dX=" + dX + ",dY=" + dY);
//
//                    if (Math.abs(dX) > Math.abs(dY)) {//左右滑动
//
////                        CCLog.print("enter test>>>>>>>>>>>>>> startx=" + startX + ",now arg0.getX()=" + ev.getX());
//                            if (dX > 0)
//                                return true;
//                            else
//                                return false;
//
//                    } else {//上下滑动
//                    }
//                case MotionEvent.ACTION_UP:
//                    break;
//            }
//        } else {
//            if (noScroll)
//                return false;
////        if ((ev.getX() - startX) > 0 && getCurrentItem() == 1) {
////            CCLog.print("父view enter test>>>>>>>>>>>>>> startx=" + startX + ",now arg0.getX()=" + ev.getX());
////
////            return false;
////        }
//            return super.onInterceptTouchEvent(ev);
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
