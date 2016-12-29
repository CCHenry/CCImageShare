package com.example.henryzheng.ccimageshare.V;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by henryzheng on 2016/12/22.
 */
public class NoBottomPaddingTextView extends TextView {
    /**
     * 需要绘制的文字
     */
    private String mText;
    /**
     * 文本的颜色
     */
    private int mTextColor;
    /**
     * 文本的大小
     */
    private int mTextSize;
    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;
    Paint.FontMetricsInt fontMetricsInt;
    public NoBottomPaddingTextView(Context context) {
        this(context, null);
        initView();

    }

    public NoBottomPaddingTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initView();

    }

    public NoBottomPaddingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化
        initView();

    }

    private void initView() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (fontMetricsInt == null) {
            fontMetricsInt = new Paint.FontMetricsInt();
            getPaint().getFontMetricsInt(fontMetricsInt);
        }
//        CCLog.print("fontMetricsInt.top:"+fontMetricsInt.top);
//        CCLog.print(" fontMetricsInt.ascent:"+ fontMetricsInt.ascent);
//        CCLog.print(" result"+ (fontMetricsInt.top - fontMetricsInt.ascent));

        canvas.translate(0, -(fontMetricsInt.top - fontMetricsInt.ascent)*2);
//        canvas.translate(0, fontMetricsInt.bottom + fontMetricsInt.ascent);

        super.onDraw(canvas);
    }
}
