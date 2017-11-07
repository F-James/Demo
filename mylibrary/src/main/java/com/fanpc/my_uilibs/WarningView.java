package com.fanpc.my_uilibs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fanpc on 2017/10/12.
 */

public class WarningView extends View {
    private Paint paint;
    private float recWigth;
    private float recHeight;
    private RectF mBounds;
    private Paint picPaint;


    public WarningView(Context context) {
        super(context);
        initPaint();
    }
    public WarningView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.warningview);
        recWigth = ta.getInteger(R.styleable.warningview_rec_width,100);
        recHeight = ta.getInteger(R.styleable.warningview_rec_height,80);
        ta.recycle();
        initPaint();
    }

    public WarningView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.warningview);
        recWigth = ta.getInteger(R.styleable.warningview_rec_width,100);
        recHeight = ta.getInteger(R.styleable.warningview_rec_height,80);
        ta.recycle();
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4);

        picPaint = new Paint();
        picPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBounds = new RectF(getLeft(),getTop(),getRight(),getBottom());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawparallelogram(canvas);
    }

    private void drawparallelogram(Canvas canvas) {

        canvas.drawRect(new RectF(mBounds.centerX()-(float)0.9*recWigth/2, mBounds.centerY() - (float)0.9*recHeight/2,mBounds.centerX() + (float)0.9*recWigth/2, mBounds.centerY() + (float)0.9*recHeight/2),paint);

        canvas.save();

        Bitmap b2 = BitmapFactory.decodeResource(getResources(), R.drawable.icon_test);

        b2 = b2.copy(Bitmap.Config.ARGB_8888, true);

        Matrix matrix_1 = new Matrix();

        matrix_1.setTranslate(mBounds.centerX(),mBounds.centerY());

        canvas.drawBitmap(b2,matrix_1,picPaint);

        canvas.restore();



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        //设置一个默认值，就是这个View的默认宽度为500，这个看我们自定义View的要求
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {//相当于我们设置为wrap_content
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {//相当于我们设置为match_parent或者为一个具体的值
            result = specSize;
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        return result;
    }
}
