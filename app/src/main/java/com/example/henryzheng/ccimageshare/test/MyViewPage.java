package com.example.henryzheng.ccimageshare.test;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by henryzheng on 2016/10/9.
 */
public class MyViewPage extends ViewPager {
    private boolean noScroll = false;
    private int startY=0;

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
    public boolean onTouchEvent(MotionEvent ev) {
//        CCLog.print("子viewpage：enter onTouchEvent");
//        switch (ev.getAction()) {
//
//            case MotionEvent.ACTION_MOVE:
//
//                int dX = (int) (ev.getX() - startX);
//                int dY = (int) (ev.getY() - startY);
//                CCLog.print("子view ACTION_MOVE>>>>>>>>>>>>>> ev.getX()=" + ev.getX() + ",startX=" + startX+",ev.getY()="+ev.getY()+",startY="+startY);
//
//                CCLog.print("子view test>>>>>>>>>>>>>> dX=" + dX + ",dY=" + dY);
//
//                if (Math.abs(dX) > Math.abs(dY)) {//左右滑动
//                    if ( dX>0&&getCurrentItem() ==0) {
//                        CCLog.print("子view enter 返回父view处理");
//                        CCLog.print(getParent().getClass().getName());
//                        return false;
//                    }
//                } else {//上下滑动
//                }
//            case MotionEvent.ACTION_UP:
//                break;
//        }
        if (noScroll)
            return false;
//        switch (arg0.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                startX = arg0.getX();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                CCLog.print("子viewpage：enter onTouchEvent handle endX="+arg0.getX()+",startX="+startX);
//
//                if (arg0.getX() -  startX< 0) {
//                    CCLog.print("子viewpage：enter onTouchEvent handle endX="+arg0.getX()+",startX="+startX);
//
//                    return false;
//                }
//                break;
//            default:
//                break;
//        }
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
