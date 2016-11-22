package com.example.henryzheng.ccimageshare.test;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.R;

import org.xutils.view.annotation.ContentView;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_test)
public class TestFragment extends BaseFragment {

    public TestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

}}
