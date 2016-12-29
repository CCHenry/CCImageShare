package com.example.henryzheng.ccimageshare.C.ImageSortType;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.C.mainfragments.ImageListBaseFragment;
import com.example.henryzheng.ccimageshare.C.mainfragments.model.ImageListBaseModel;
import com.example.henryzheng.ccimageshare.C.mainfragments.model.ImageSortInfoListModel;
import com.example.henryzheng.ccimageshare.M.Contants.MyContonts;
import com.example.henryzheng.ccimageshare.R;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

@ContentView(R.layout.activity_image_sort_info_list)
public class ImageSortInfoListActivity extends BaseActivity {
    //    @ViewInject(R.id.fragment_container)
//    private fra rl0;
    @ViewInject(R.id.tv0)
    private TextView tv0;
    @ViewInject(R.id.iv)
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Intent intent = getIntent();
        String url = intent.getStringExtra("loadListUrl");
        String title = intent.getStringExtra("title");
        tv0.setText(title);
        ImageListBaseModel imageListBaseModel = new ImageSortInfoListModel();
        imageListBaseModel.setUrl(url);
        BaseFragment fragment = ImageListBaseFragment.newInstance(imageListBaseModel);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (new File(MyContonts.bgCahe).exists()) {
            ImageOptions imageOptions = new ImageOptions.Builder()
                    .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                    .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                    .setFadeIn(true)
                    .setUseMemCache(false)
                    .build();
            x.image().bind(iv, MyContonts.bgCahe, imageOptions);
            iv.getDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        }
    }
}
