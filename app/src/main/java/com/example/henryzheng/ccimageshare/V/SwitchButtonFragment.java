package com.example.henryzheng.ccimageshare.V;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.henryzheng.ccimageshare.C.Base.BaseFragment;
import com.example.henryzheng.ccimageshare.R;
import com.example.henryzheng.ccimageshare.M.common.CCLog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;


@ContentView(R.layout.fragment_switch_button)
public class SwitchButtonFragment extends BaseFragment {
    @ViewInject(R.id.btn)
    private TextView btn;
    @ViewInject(R.id.rl)
    private RelativeLayout rl;
    Boolean select = false;

   static OnSwitchClickListner _switchOnClickListner;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Event(value = R.id.rl,type = View.OnClickListener.class)
    private void onclick(View view) {
        CCLog.print("change");
//        ObjectAnimator animator = ObjectAnimator.ofFloat(btn, "rotation", 0f, 360f);
//        animator.setDuration(1000);
//        animator.start();
        ObjectAnimator animator = ObjectAnimator.ofFloat(rl, "scaleX", 1f, 1.3f, 1f);
        animator.setDuration(400);
        animator.start();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(rl, "scaleY", 1f, 1.3f, 1f);
        animator1.setDuration(400);
        animator1.start();
        _switchOnClickListner.onClick();
    }

    /**
     * 设置按钮监听
     * @param switchOnClickListner
     */
    public static void setOnSwitchClickListener(OnSwitchClickListner switchOnClickListner) {
        _switchOnClickListner = switchOnClickListner;
    }

    public interface OnSwitchClickListner {
        void onClick();
    }
}
