package com.example.henryzheng.ccimageshare.V;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.henryzheng.ccimageshare.R;

/**
 * Created by henryzheng on 2016/12/13.
 */
public class NavigationView extends RelativeLayout implements View.OnClickListener {
    Context context;
    LinearLayout lin0;// title的layout
    LinearLayout bottomLin;//底部的线的布局
    public BaseViewPage viewPager;
    TextView tv0;
    TextView tv1;
    TextView tv2;
    float mPositionOffset = 0;
    int titleWidth = 0;
    float instanceX = 0;
    RelativeLayout.LayoutParams bottomLinLayoutParams;

    public NavigationView(Context context) {
        super(context);
    }

    public NavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        addView(context);
    }

    private void addView(Context context) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        RelativeLayout nv = (RelativeLayout) mInflater.inflate(R.layout
                .relativelayout_navigation1, null);
        tv0 = (TextView) nv.findViewById(R.id.tv0);
        tv1 = (TextView) nv.findViewById(R.id.tv1);
        tv2 = (TextView) nv.findViewById(R.id.tv2);
        lin0 = (LinearLayout) nv.findViewById(R.id.lin0);
        bottomLin = (LinearLayout) nv.findViewById(R.id.lin5);
        tv0.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        lin0.measure(width, height);
        height = lin0.getMeasuredHeight();
        width = lin0.getMeasuredWidth();
        titleWidth = width / 3;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) bottomLin
                .getLayoutParams();
        layoutParams.width = titleWidth;
        bottomLin.setLayoutParams(layoutParams);
        final int[] location = new int[2];
        lin0.getLocationOnScreen(location);
        instanceX = location[0];
        bottomLinLayoutParams = (RelativeLayout.LayoutParams) bottomLin.getLayoutParams();
        addView(nv);

    }

    /**
     * 设置viewpager，监听viewpager，让标识移动
     *
     * @param viewPager
     */
    public void setMainPage(final MainFragmentViewPage viewPager) {
        this.viewPager = viewPager;
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
                scrollOnListener(position, positionOffset);
            }

            /**
             * 滚动处理
             * @param position
             * @param positionOffset
             */
            private void scrollOnListener(int position, float positionOffset) {


                mPositionOffset = positionOffset;
                switch (position) {
                    case 0:
                        bottomLinLayoutParams.leftMargin = (int) (titleWidth * positionOffset);
                        bottomLin.setLayoutParams(bottomLinLayoutParams);
                        bottomLin.requestLayout();
                        break;
                    case 1:
                        bottomLinLayoutParams.leftMargin = titleWidth + (int) (titleWidth *
                                positionOffset);
                        bottomLin.setLayoutParams(bottomLinLayoutParams);
                        bottomLin.requestLayout();
                        break;
                    case 2:
                        bottomLinLayoutParams.leftMargin = titleWidth * 2 + (int) (titleWidth *
                                positionOffset);
                        bottomLin.setLayoutParams(bottomLinLayoutParams);
                        bottomLin.requestLayout();
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv0:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv1:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv2:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
