package com.lmm.wddog.util.fresco;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.widget.ImageView;

import com.facebook.drawee.drawable.ScalingUtils;

/**
 * Created by Administrator on 2017/1/27.
 */

public class MatrixScaleType implements ScalingUtils.ScaleType {
    @Override
    public Matrix getTransform(Matrix outTransform, Rect parentBounds, int childWidth, int childHeight, float focusX, float focusY) {
        final int parentWidth = parentBounds.width();
        final int parentHeight = parentBounds.height();

        final float scaleX = (float) parentWidth / (float) childWidth;
        final float scaleY = (float) parentHeight / (float) childHeight;

        float scale = 1.0f;
        float dx = 0;
        float dy = 0;
        dx = parentBounds.left;
        dy = parentBounds.top;
        outTransform.setScale(scaleX, scaleX);
        outTransform.postTranslate((int) (dx + 0.5f), (int) (dy + 0.5f));
        return outTransform;
    }
}
