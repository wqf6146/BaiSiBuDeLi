package com.lmm.wddog.util;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

import com.lmm.wddog.App;

import java.util.Random;

public class CommonUtils {
    public static int dip2px(float dpValue) {
        final float scale =  App.newInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale =  App.newInstance().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static Point getDisplaySize(){
        WindowManager wm = (WindowManager) App.newInstance().getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return point;
    }

    public static int getColor(int resid) {
        return App.newInstance().getResources().getColor(resid);
    }

    public static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max) % (max - min + 1) + min;
    }
}
