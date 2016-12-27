package com.example.henryzheng.ccimageshare.V;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import com.example.henryzheng.ccimageshare.M.utils.CCLog;

/**
 * Created by henryzheng on 2016/12/27.
 */
public class MyHorizontalView  extends HorizontalScrollView{
    private float startX,startY;

    public MyHorizontalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
                CCLog.print("子viewpage：enter onTouchEvent");
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                startX=ev.getX();
//                startY=ev.getY();
//            case MotionEvent.ACTION_MOVE:
//                int dX = (int) (ev.getX() - startX);
//                int dY = (int) (ev.getY() - startY);
//
//                if (Math.abs(dX) > Math.abs(dY)) {//左右滑动
//                        CCLog.print("子view enter 返回父view处理");
//                        CCLog.print(getParent().getClass().getName());
//                        return false;
//
//                } else {//上下滑动
//                }
//            case MotionEvent.ACTION_UP:
//                break;
//        }
        return super.dispatchTouchEvent(ev);

    }
}
