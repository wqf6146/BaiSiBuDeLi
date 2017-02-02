package com.lmm.wddog.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/1/27.
 */

public class DieFrameLayout extends FrameLayout {
    public DieFrameLayout(Context context, AttributeSet attr){
        super(context,attr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
