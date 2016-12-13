package com.example.henryzheng.ccimageshare.V;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.henryzheng.ccimageshare.M.common.CCLog;

/**
 * Created by henryzheng on 2016/10/9.
 */
public class MainFragmentViewPage extends BaseViewPage {
    private boolean noScroll = false;
    private int startY = 0;
    private View displayView;
    private float curTranslationY;

    public MainFragmentViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainFragmentViewPage(Context context) {
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
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                startY = (int) ev.getY();

                break;
            case MotionEvent.ACTION_MOVE:

                int dX = (int) (ev.getX() - startX);
                int dY = (int) (ev.getY() - startY);

                if (Math.abs(dX) > Math.abs(dY)) {//左右滑动

                } else {//上下滑动
                    if (dY<-30){
                        CCLog.print("向下滑动");
                        ObjectAnimator animator = ObjectAnimator.ofFloat(displayView, "translationY", curTranslationY, -100f);
                        animator.setDuration(900);
                        animator.start();
                    }

                    else if(dY>30){
                        ObjectAnimator animator = ObjectAnimator.ofFloat(displayView, "translationY",  -100f, curTranslationY);
                        animator.setDuration(900);
                        animator.start();
                    }

                }
            case MotionEvent.ACTION_UP:
                break;
        }
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

    public void setDisplayView(View view){
        displayView=view;
        curTranslationY = displayView.getTranslationY();

    }
}
