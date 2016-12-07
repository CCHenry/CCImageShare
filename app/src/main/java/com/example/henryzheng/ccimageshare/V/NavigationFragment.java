package com.example.henryzheng.ccimageshare.V;


import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 自定义控件
 */
@ContentView(R.layout.fragment_navigation)
public class NavigationFragment extends BaseFragment {
    @ViewInject(R.id.lin5)
    LinearLayout lin;// 标签的layout
    @ViewInject(R.id.lin0)
    LinearLayout lin0;// title的layout
    int titleWidth = 0;
    float instanceX = 0;
    private ViewPager viewPager;
    @ViewInject(R.id.tv0)
    TextView tv0;
    @ViewInject(R.id.tv1)
    TextView tv1;
    @ViewInject(R.id.tv2)
    TextView tv2;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        lin0.measure(width, height);
        height = lin0.getMeasuredHeight();
        width = lin0.getMeasuredWidth();
        titleWidth = width / 3;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) lin.getLayoutParams();
        layoutParams.width = titleWidth;
        lin.setLayoutParams(layoutParams);
        final int[] location = new int[2];
        lin0.getLocationOnScreen(location);
        instanceX = location[0];
//        CCLog.print("titleWidth:" + titleWidth + " instanceX:" + instanceX);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    /**
     * 设置viewpager，监听viewpager，让标识移动
     *
     * @param viewPager
     */
    public void setMainPage(ViewPager viewPager) {
        this.viewPager = viewPager;
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int position = 0;
            Boolean isIni=false;
            LinearLayout testLin;
            RelativeLayout.LayoutParams testLayoutParams;
            TextView tv0;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (!isIni){
                    isIni=true;
                    return;
                }else
                {
                    testLin = (LinearLayout) getActivity().findViewById(R.id.lin5);
                    testLayoutParams = (RelativeLayout.LayoutParams) lin.getLayoutParams();
                    tv0= (TextView) getActivity().findViewById(R.id.tv0);
                }
                switch (position) {
                    case 0:
                        testLayoutParams.leftMargin = (int) (titleWidth * positionOffset);
                        testLin.setLayoutParams(testLayoutParams);
//                        tv0.setTextSize(TypedValue.COMPLEX_UNIT_PX, 19);
//                        tv1.setTextSize(TypedValue.COMPLEX_UNIT_PX, 14);
//                        tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, 14);
                        testLin.requestLayout();
//                        tv0.requestLayout();
//                        tv1.requestLayout();
//                        tv2.requestLayout();
//                        tv0.postInvalidate();
//                        tv1.postInvalidate();
//                        tv2.postInvalidate();
//                        lin0.requestLayout();
                        tv1.setText("sadasdas");
                        tv0.setText("asdasdas");
                        break;
                    case 1:
                        testLayoutParams.leftMargin = titleWidth + (int) (titleWidth * positionOffset);
                        testLin.setLayoutParams(testLayoutParams);
                        tv1.setTextSize(TypedValue.COMPLEX_UNIT_PX, 19);
                        tv0.setTextSize(TypedValue.COMPLEX_UNIT_PX, 14);
                        tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, 14);
                        testLin.requestLayout();
                        tv0.requestLayout();
                        tv1.requestLayout();
                        tv2.requestLayout();
                        tv0.postInvalidate();
                        tv1.postInvalidate();
                        tv2.postInvalidate();
                        tv1.setText("asddddddddddddddddd");
                        lin0.requestLayout();
                        break;
                    case 2:
                        testLayoutParams.leftMargin = titleWidth * 2 + (int) (titleWidth * positionOffset);
                        testLin.setLayoutParams(testLayoutParams);
                        tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, 19);
                        tv1.setTextSize(TypedValue.COMPLEX_UNIT_PX, 14);
                        tv0.setTextSize(TypedValue.COMPLEX_UNIT_PX, 14);
                        testLin.requestLayout();
                        tv0.requestLayout();
                        tv1.requestLayout();
                        tv2.requestLayout();
                        tv0.postInvalidate();
                        tv1.postInvalidate();
                        tv2.postInvalidate();
                        lin0.requestLayout();

                        break;
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Event(value = R.id.btn)
    private void onclick(View view) {

    }

    @Override
    public void OnHandlerListener(Message msg) {
        super.OnHandlerListener(msg);

    }
}
