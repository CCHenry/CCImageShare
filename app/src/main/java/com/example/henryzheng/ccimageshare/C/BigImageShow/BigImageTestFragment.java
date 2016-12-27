package com.example.henryzheng.ccimageshare.C.BigImageShow;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.henryzheng.ccimageshare.C.Base.BaseActivity;
import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.M.ZuiMeiModel.Image;
import com.example.henryzheng.ccimageshare.M.utils.StringUtil;
import com.example.henryzheng.ccimageshare.R;
import com.example.henryzheng.ccimageshare.test.MyHorizonScrollView;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


@ContentView(R.layout.fragment_big_image_test)
public class BigImageTestFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    Image bigImage = new Image();
    @ViewInject(R.id.iv)
    private ImageView iv;
    @ViewInject(R.id.load)
    private ImageView load;
    @ViewInject(R.id.tv0)
    private TextView tv0;
    @ViewInject(R.id.horizontalScrollView)
    private MyHorizonScrollView horizontalScrollView;
    private ImageOptions imageOptions;

    public void setBigImage(Image bigImage) {
        this.bigImage = bigImage;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SensorManager sm = (SensorManager) (getActivity()).getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //添加重力感应侦听，并实现其方法，
        horizontalScrollView.setSmoothScrollingEnabled(true);

        SensorEventListener sel = new SensorEventListener() {
            public void onSensorChanged(final SensorEvent se) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
//                        double x = se.values[SensorManager.DATA_X];
//                        double y = se.values[SensorManager.DATA_Y];
//                        double z = se.values[SensorManager.DATA_Z];
//                        CCLog.print("x=" + (int) x + "y=" + (int) y + "z=" + (int) z);
//                        ViewGroup.LayoutParams layoutParams=iv.getLayoutParams();
////                        horizontalScrollView.setScrollIndicators();
////                        horizontalScrollView.
////                        if (horizontalScrollView.getsc)
//                        horizontalScrollView.smoothScrollBy((int) x,0);


                    }
                });


            }

            public void onAccuracyChanged(Sensor arg0, int arg1) {
            }
        };
        //注册Listener，SENSOR_DELAY_GAME为检测的精确度，
        sm.registerListener(sel, sensor, SensorManager.SENSOR_DELAY_GAME);
//    horizontalScrollView.setOnTouchListener(new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            return true;
//        }
//    });
    }

    @Override
    public void onResume() {
        super.onResume();


        if (bigImage.getPub_time() != null) {
            tv0.setText(bigImage.getPub_time());
            ViewGroup.LayoutParams parms = iv.getLayoutParams();
            parms.height = ((BaseActivity) (getActivity())).getHight();
            parms.width = (int) (((BaseActivity) (getActivity())).getHight() / StringUtil
                    .toDouble(bigImage.getHeight(), 0) * StringUtil.toDouble

                    (bigImage.getWidth(), 0));
            iv.setLayoutParams(parms);
            iv.requestLayout();

            imageOptions = new ImageOptions.Builder()
//                .setSize(((BaseActivity) getActivity()).getWidth(), ((BaseActivity) getActivity()
                    .setIgnoreGif(false)
                    .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                    .setUseMemCache(true)
                    .setFadeIn(true)
                    .build();
            x.image().bind(iv, bigImage.getOrigin_image_url(), imageOptions, new
                    CustomBitmapLoadCallBack());
        }
    }

    public class CustomBitmapLoadCallBack implements Callback.ProgressCallback<Drawable> {
        AnimationDrawable mAnimate;

        @Override
        public void onWaiting() {
            load.setVisibility(View.VISIBLE);
            mAnimate = (AnimationDrawable) load.getBackground();
            mAnimate.setOneShot(false);
            mAnimate.start();
        }

        @Override
        public void onStarted() {
        }

        @Override
        public void onLoading(long total, long current, boolean isDownloading) {
        }

        @Override
        public void onSuccess(Drawable result) {
            mAnimate.stop();
            load.setVisibility(View.GONE);

        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
        }

        @Override
        public void onCancelled(CancelledException cex) {
        }

        @Override
        public void onFinished() {
        }
    }

}
