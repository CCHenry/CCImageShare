package com.example.henryzheng.ccimageshare.C.BigImageShow;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.C.mainfragments.model.ImageListBaseModel;
import com.example.henryzheng.ccimageshare.M.Contants.MyContonts;
import com.example.henryzheng.ccimageshare.M.ZuiMeiModel.Image;
import com.example.henryzheng.ccimageshare.R;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.List;

@ContentView(R.layout.activity_big_image_show)
public class BigImageShowActivity extends BaseActivity {
    @ViewInject(R.id.fragment_big_image_show)
    private BigImageShowFragment bigImageShowFragment;
    @ViewInject(R.id.rl0)
    private RelativeLayout rl0;
    @ViewInject(R.id.iv)
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Image> images = (List<Image>) getIntent().getSerializableExtra("images");
        ImageListBaseModel imageListBaseModel = (ImageListBaseModel) getIntent()
                .getSerializableExtra("imageListBaseModel");
        int position = getIntent().getIntExtra("position", 0);
        BigImageShowFragment.setImagesAndModel(images, imageListBaseModel, position);
        registerReceiver(_receiver, new IntentFilter(Intent
                .ACTION_WALLPAPER_CHANGED));

    }

    private BroadcastReceiver _receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_WALLPAPER_CHANGED)) {
                Toast.makeText(BigImageShowActivity.this, "已更改墙纸", Toast.LENGTH_SHORT).show();
                _cCdialog.hide();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (new File(MyContonts.bgCahe).exists()){
            ImageOptions imageOptions = new ImageOptions.Builder()
                    .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                    .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                    .setFadeIn(true)
                    .setUseMemCache(false)
                    .build();
            x.image().bind(iv,MyContonts.bgCahe,imageOptions);
            iv.getDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(_receiver);
    }
}
