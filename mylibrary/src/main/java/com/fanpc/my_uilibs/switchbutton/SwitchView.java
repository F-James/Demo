package com.fanpc.my_uilibs.switchbutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by fanpc on 2017/11/7.
 */

public class SwitchView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();

    private int mWidth;
    private int mHeight;

    private float sWidth;
    private float sHeight;
    private float sLeft,sTop,sRight,sBottom;
    private float sCenterX,sCenterY;

    private float sAnim;

    private boolean isOn;

    public SwitchView(Context context) {
        super(context);
    }

    public SwitchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xffcccccc);
        canvas.drawPath(path, paint); // 画出田径场

        sAnim = sAnim - 0.1f > 0 ? sAnim - 0.1f : 0; // 动画标示 ，重绘10次
        final float scale = 0.98f * (isOn ? sAnim : 1 - sAnim); //缩放大小参数随sAnim变化而变化
        canvas.save();
        canvas.scale(scale,scale,sCenterX,sCenterY);
        paint.setColor(0xffffffff);
        canvas.drawPath(path, paint);
        canvas.restore();

        paint.reset();
        if (sAnim > 0) invalidate(); // 继续重绘
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heghtSize = (int) (widthSize*0.65f);
        setMeasuredDimension(widthSize,heghtSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        sLeft = sTop = 0;
        sRight = mWidth;
        sBottom = mHeight*0.8f;

        sCenterX = (sRight+sLeft)/2;
        sCenterY = (sTop+sBottom)/2;

        RectF rectF = new RectF(sLeft,sTop,sBottom,sBottom);
        path.arcTo(rectF, 90, 180);
        rectF.left = sRight - sBottom;
        rectF.right = sRight;
        path.arcTo(rectF, 270, 180);
        path.close();    // path准备田径场的路径

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                sAnim = 1; // 动画标示
                isOn = !isOn; // 状态标示 ， 开关
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }
}
