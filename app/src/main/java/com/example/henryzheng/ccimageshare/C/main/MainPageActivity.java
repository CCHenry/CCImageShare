package com.example.henryzheng.ccimageshare.C.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.C.ImageSortType.fragment.ImageSortFragment;
import com.example.henryzheng.ccimageshare.M.utils.CCLog;
import com.example.henryzheng.ccimageshare.R;
import com.example.henryzheng.ccimageshare.V.BaseViewPage;
import com.example.henryzheng.ccimageshare.V.MyScroll;
import com.example.henryzheng.ccimageshare.V.SwitchButtonFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main_page)
public class MainPageActivity extends BaseActivity{
    int viewSwitch = -1;
    private List<BaseFragment> _fragments;
    @ViewInject(R.id.mainViewPager)
    private BaseViewPage mainViewPager;
    @ViewInject(R.id.rl)
    private RelativeLayout rl;
    @ViewInject(R.id.switch_fg)
    private SwitchButtonFragment switchButtonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initFragment();
        MainPageAdapt mainPageAdapt = new MainPageAdapt(getSupportFragmentManager(), _fragments);
        mainViewPager.setAdapter(mainPageAdapt);
        mainViewPager.setCurrentItem(0);
        SwitchButtonFragment.setOnSwitchClickListener(new SwitchButtonFragment.OnSwitchClickListner() {
            int change = -1;

            @Override
            public void onClick() {
                change = -change;
                if (change == -1)
                    mainViewPager.setCurrentItem(0);
                else
                    mainViewPager.setCurrentItem(1);
                CCLog.print("change" + change);
            }
        });
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        _fragments = new ArrayList<>();
        _fragments.add(new ImageSortFragment());
//        MainFragment m=
        _fragments.add(new MainFragment());

    }

    private void setViewPagerScrollSpeed(ViewPager viewPager, int i) {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            MyScroll scroller = new MyScroll(viewPager.getContext());
            scroller.setmDuration(i);
            mScroller.set(viewPager, scroller);
        } catch (NoSuchFieldException e) {

        } catch (IllegalArgumentException e) {

        } catch (IllegalAccessException e) {

        }
    }



    private class MainPageAdapt extends FragmentPagerAdapter {
        private List<BaseFragment> _fragments;
        private boolean isCanScroll = true;

        public MainPageAdapt(FragmentManager fm, List<BaseFragment> fragment) {
            super(fm);
            _fragments = fragment;
        }

        @Override
        public Fragment getItem(int position) {
            return _fragments.get(position);
        }

        @Override
        public int getCount() {
            return _fragments.size();
        }

        public void setScanScroll(boolean isCanScroll) {
            this.isCanScroll = isCanScroll;
        }
    }
}
