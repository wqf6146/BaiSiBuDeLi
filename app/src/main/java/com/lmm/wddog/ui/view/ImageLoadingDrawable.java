package com.lmm.wddog.ui.view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import com.facebook.drawee.drawable.DrawableUtils;
import com.lmm.wddog.App;
import com.lmm.wddog.R;

public class ImageLoadingDrawable extends Drawable {

    private Paint mFontPaint;

    private Paint mRingBackgroundPaint;
    private int mRingBackgroundColor;
    // 画圆环的画笔
    private Paint mRingPaint;
    // 圆环颜色
    private int mRingColor;
    // 半径
    private float mRadius;
    // 圆环半径
    private float mRingRadius;
    // 圆环宽度
    private float mStrokeWidth;
    // 圆心x坐标
    private int mXCenter;
    // 圆心y坐标
    private int mYCenter;
    // 总进度
    private int mTotalProgress = 10000;
    // 当前进度
    private int mProgress;


    public ImageLoadingDrawable(){
        initAttrs();
    }

    private void initAttrs() {
        mRadius = 100;
        mStrokeWidth = 15;
        mRingBackgroundColor = App.newInstance().getResources().getColor(R.color.graybg);;
        mRingColor = App.newInstance().getResources().getColor(R.color.gray);
        mRingRadius = mRadius + mStrokeWidth / 2;
        initVariable();
    }

    private void initVariable() {
        mRingBackgroundPaint = new Paint();
        mRingBackgroundPaint.setAntiAlias(true);
        mRingBackgroundPaint.setColor(mRingBackgroundColor);
        mRingBackgroundPaint.setStyle(Paint.Style.STROKE);
        mRingBackgroundPaint.setStrokeWidth(mStrokeWidth);

        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(mStrokeWidth);

        mFontPaint = new Paint();
        mFontPaint.setAntiAlias(true);
        mFontPaint.setColor(mRingColor);
        mFontPaint.setStyle(Paint.Style.FILL);
        mFontPaint.setTextSize(50);
    }

    @Override
    public void draw(Canvas canvas) {
        drawBar(canvas,mTotalProgress,mRingBackgroundPaint);
        drawBar(canvas,mProgress,mRingPaint);
        String progress = String.valueOf(mProgress/100)+"%";
        canvas.drawText(progress,mXCenter - mFontPaint.measureText(progress)/2,mYCenter+mStrokeWidth,mFontPaint);
    }

    private void drawBar(Canvas canvas, int level, Paint paint) {
        if (level > 0 ) {
            Rect bound= getBounds();
            mXCenter = bound.centerX();
            mYCenter = bound.centerY();
            RectF oval = new RectF();
            oval.left = (mXCenter - mRingRadius);
            oval.top = (mYCenter - mRingRadius);
            oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
            oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
            canvas.drawArc(oval, -90, ((float) level / mTotalProgress) * 360, false, paint); //
        }
    }

    @Override
    protected boolean onLevelChange(int level) {
        mProgress = level;
        if(level > 0 && level < 10000) {
            invalidateSelf();
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void setAlpha(int alpha) {
        mRingPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mRingPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return DrawableUtils.getOpacityFromColor(this.mRingPaint.getColor());
    }
}