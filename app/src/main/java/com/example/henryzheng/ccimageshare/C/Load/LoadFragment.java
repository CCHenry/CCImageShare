package com.example.henryzheng.ccimageshare.C.Load;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.C.main.MainPageActivity;
import com.example.henryzheng.ccimageshare.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.fragment_load)
public class LoadFragment extends BaseFragment {
    @ViewInject(R.id.iv0)
    private ImageView iv0;
    @ViewInject(R.id.iv1)
    private ImageView iv1;
    long delayTime=1700;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setBgScale();
        setLogoAlphaAndMove();
    }

    private void setBgScale() {

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 1f,
                1.5f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 1f,
                1.5f);
        ObjectAnimator.ofPropertyValuesHolder(iv0, pvhX,pvhY).setDuration(delayTime).start();
        iv0.getDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
    }
    private void setLogoAlphaAndMove() {
        PropertyValuesHolder moveY= PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, iv1.getTranslationY()+300f,
                iv1.getTranslationY()-iv1.getLayoutParams().height/2);
        PropertyValuesHolder scale= PropertyValuesHolder.ofFloat(View.ALPHA, 0,
                1f);
        ObjectAnimator animator= ObjectAnimator.ofPropertyValuesHolder(iv1, scale,moveY).setDuration(delayTime);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(getActivity(), MainPageActivity.class));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }
}
