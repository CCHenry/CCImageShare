package com.example.henryzheng.ccimageshare.C.BigImageShow;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.C.mainfragments.model.ImageListBaseModel;
import com.example.henryzheng.ccimageshare.M.ZuiMeiModel.Image;
import com.example.henryzheng.ccimageshare.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.activity_big_image_show)
public class BigImageShowActivity extends BaseActivity {
    @ViewInject(R.id.fragment_big_image_show)
    private BigImageShowFragment bigImageShowFragment;
    @ViewInject(R.id.rl0)
    private RelativeLayout rl0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Image> images = (List<Image>) getIntent().getSerializableExtra("images");
       ImageListBaseModel imageListBaseModel = (ImageListBaseModel) getIntent().getSerializableExtra("imageListBaseModel");
        int position=getIntent().getIntExtra("position",0);
        BigImageShowFragment.setImagesAndModel(images,imageListBaseModel,position);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
