package com.example.henryzheng.ccimageshare.C.BigImageShow;

import android.animation.ValueAnimator;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.M.ZuiMeiModel.Image;
import com.example.henryzheng.ccimageshare.R;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;


@ContentView(R.layout.fragment_big_image)
public class BigImageFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    Image bigImage = new Image();
    @ViewInject(R.id.iv)
    private ImageView iv;
    @ViewInject(R.id.load)
    private ImageView load;
    @ViewInject(R.id.tv0)
    private TextView tv0;
    @ViewInject(R.id.lin0)
    private LinearLayout lin0;
    @ViewInject(R.id.lin1)
    private LinearLayout lin1;
    @ViewInject(R.id.rl)
    private RelativeLayout rl;
    private ImageOptions imageOptions;
    private Bitmap bitmap;

    public void setBigImage(Image bigImage) {
        this.bigImage = bigImage;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SensorManager sm = (SensorManager) (getActivity()).getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //添加重力感应侦听，并实现其方法，
        SensorEventListener sel = new SensorEventListener() {
            public void onSensorChanged(final SensorEvent se) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

            public void onAccuracyChanged(Sensor arg0, int arg1) {
            }
        };
        //注册Listener，SENSOR_DELAY_GAME为检测的精确度，
        sm.registerListener(sel, sensor, SensorManager.SENSOR_DELAY_GAME);
        lin0.setOnTouchListener(new View.OnTouchListener() {
            public LinearLayout.LayoutParams parms;
            public float moveY;
            public float startY = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    startY = event.getY();
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    moveY = event.getY();
                    parms = (LinearLayout.LayoutParams) lin1.getLayoutParams();
                    parms.bottomMargin = (int) ((startY - moveY) * 0.3f);
                    lin1.requestLayout();
//                    rl.requestLayout();
//                    lin0.requestLayout();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    int temp = parms.bottomMargin;
//                    for (int i = 0; i < temp; i++) {
//
//                        parms.bottomMargin = (int) (parms.bottomMargin -1f);
//                        lin1.requestLayout();
//
//
//                    }
                    ValueAnimator valueAnimator = ValueAnimator.ofInt(temp, 0);
                    valueAnimator.setTarget(lin1);
                    valueAnimator.setDuration(200).start();
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            parms = (LinearLayout.LayoutParams) lin1.getLayoutParams();
                            parms.bottomMargin = (int) animation.getAnimatedValue();
                            lin1.requestLayout();
                            rl.requestLayout();
                        }
                    });

                }
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();


        if (bigImage.getPub_time() != null) {
            tv0.setText(bigImage.getPub_time());}
//            ViewGroup.LayoutParams parms = iv.getLayoutParams();
//            parms.height = ((BaseActivity) (getActivity())).getHight();
//            parms.width = (int) (((BaseActivity) (getActivity())).getHight() / StringUtil
//                    .toDouble(bigImage.getHeight(), 0) * StringUtil.toDouble
//
//                    (bigImage.getWidth(), 0));
//            iv.setLayoutParams(parms);
//            iv.requestLayout();

            imageOptions = new ImageOptions.Builder ()
//                .setSize(((BaseActivity) getActivity()).getWidth(), ((BaseActivity) getActivity()
                    .setIgnoreGif(false)
                    .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                    .setUseMemCache(true)
                    .setFadeIn(true)
                    .build();
            x.image().bind(iv, bigImage.getOrigin_image_url(), imageOptions, new
                    CustomBitmapLoadCallBack());



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
            drawableToBitamp(result);
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

    @Event(value = R.id.btn0, type = View.OnClickListener.class)
    private void btnOnClick(View view) {
//        iv.setDrawingCacheEnabled(true);
//        Bitmap bm = iv.getDrawingCache();
//        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getActivity());
//        try {
//            wallpaperManager.setBitmap(bm);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        wallpaperManager.suggestDesiredDimensions(((BaseActivity) getActivity()).getWidth(), (
//                (BaseActivity) getActivity()).getHight());
//        iv.setDrawingCacheEnabled(false);
        WallpaperManager manager = WallpaperManager.getInstance(getActivity());

        try {
            manager.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawableToBitamp(Drawable drawable) {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        System.out.println("Drawable转Bitmap");
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        bitmap = Bitmap.createBitmap(w, h, config);
        //注意，下面三行代码要用到，否在在View或者surfaceview里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
    }
}
