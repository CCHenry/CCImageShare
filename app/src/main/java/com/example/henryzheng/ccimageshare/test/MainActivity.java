package com.example.henryzheng.ccimageshare.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.C.ImageShowRecycle.RecyclerImageFrament;
import com.example.henryzheng.ccimageshare.C.ImageSortType.fragment.ImageSortFragment;
import com.example.henryzheng.ccimageshare.R;
import com.example.henryzheng.ccimageshare.V.MainFragmentViewPage;
import com.example.henryzheng.ccimageshare.V.NavigationFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewInject(R.id.rl)
    private RelativeLayout rl;//menu按钮
    @ViewInject(R.id.mainViewPager)
    private MainFragmentViewPage mainViewPager;
    @ViewInject(R.id.fm)
    private NavigationFragment navigationFragment;

    private List<BaseFragment> _fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display mDisplay = getWindowManager().getDefaultDisplay();
        initFragment();
        mainViewPager.setAdapter(new MainPageAdapt(getSupportFragmentManager(), _fragments));
    }

    @Override
    protected void onResume() {
        super.onResume();
        NavigationFragment fragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.fm);
        fragment.setMainPage(mainViewPager);

    }


    private void initFragment() {
        _fragments = new ArrayList<>();
        _fragments.add(new ImageSortFragment());
        _fragments.add(new RecyclerImageFrament());
        _fragments.add(new ImageSortFragment());

    }

    private class MainPageAdapt extends FragmentPagerAdapter {
        private List<BaseFragment> _fragments;

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
    }

    @Event(value = {R.id.rl})
    private void onclick(View view) {
        switch (view.getId()) {
            case R.id.rl:

                break;
        }
    }
}
