package com.example.henryzheng.ccimageshare.V;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.henryzheng.ccimageshare.R;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by henryzheng on 2016/12/13.
 */
public class NavigationView extends RelativeLayout{
    Activity context;
    @ViewInject(R.id.lin5)
    LinearLayout lin;// 标签的layout
    @ViewInject(R.id.lin0)
    LinearLayout lin0;// title的layout
    int titleWidth = 0;
    float instanceX = 0;
    public BaseViewPage viewPager;
    //    @ViewInject(R.id.tv0)
    TextView tv0;
    //    @ViewInject(R.id.tv1)
    TextView tv1;
    //    @ViewInject(R.id.tv2)
    TextView tv2;

    float mPositionOffset = 0;
    public NavigationView(Context context) {
        super(context);
    }

    public NavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addView(context);
        this.context= (Activity) context;
    }
    private void addView(Context context){
        LayoutInflater mInflater = LayoutInflater.from(context);
        RelativeLayout tv=(RelativeLayout)mInflater.inflate(R.layout.relativelayout_navigation,null);
        addView(tv);
    }
    /**
     * 设置viewpager，监听viewpager，让标识移动
     *
     * @param viewPager
     */
    public void setMainPage(final MainActivityViewPage viewPager) {


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            Boolean isIni = false;
            LinearLayout bottomLin;
            RelativeLayout.LayoutParams linLayoutParams;

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
                /**
                 * 第一次会默认加载，此时这个fragment还没完全绘制好，故做初始化判断屏蔽
                 */
                if (!isIni) {
                    isIni = true;
                    setTitleListener();
                    LinearLayout disPlayView = (LinearLayout) context.findViewById(R.id.lin0);
//                    viewPager.setDisplayView(NavigationFragment.this);
                    return;
                } else {
                    /**
                     * 在其他fragment里面操作不能用注解
                     */
                    bottomLin = (LinearLayout) context.findViewById(R.id.lin5);
                    linLayoutParams = (RelativeLayout.LayoutParams) lin.getLayoutParams();
                }
                mPositionOffset = positionOffset;
                switch (position) {
                    case 0:
                        linLayoutParams.leftMargin = (int) (titleWidth * positionOffset);
                        bottomLin.setLayoutParams(linLayoutParams);
                        bottomLin.requestLayout();
                        break;
                    case 1:
                        linLayoutParams.leftMargin = titleWidth + (int) (titleWidth *
                                positionOffset);
                        bottomLin.setLayoutParams(linLayoutParams);
                        bottomLin.requestLayout();
                        break;
                    case 2:
                        linLayoutParams.leftMargin = titleWidth * 2 + (int) (titleWidth *
                                positionOffset);
                        bottomLin.setLayoutParams(linLayoutParams);
                        bottomLin.requestLayout();

                        break;
                }
            }

            /**
             * 在其他fragment里面操作不能用注解
             */
            private void setTitleListener() {
                tv0 = (TextView) context.findViewById(R.id.tv0);
                tv1 = (TextView) context.findViewById(R.id.tv1);
                tv2 = (TextView) context.findViewById(R.id.tv2);
                tv0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(0);
                    }
                });
                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(1);

                    }
                });
                tv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(2);

                    }
                });
            }


        });


    }

}
