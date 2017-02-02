package com.lmm.wddog.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lmm.wddog.BaseActivity;
import com.lmm.wddog.R;
import com.lmm.wddog.util.CommonUtils;
import com.lmm.wddog.util.StatusBarCompat;
import com.lmm.wddog.util.fresco.FrescoUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.yokeyword.fragmentation.SupportActivity;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/1/31.
 */

public class WelcomeActivity extends SupportActivity {

    SimpleDraweeView mImg;

    private static final int COUNT_DOWN_TIME = 2200;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    public void init(){
        mImg = (SimpleDraweeView)findViewById(R.id.aw_img);
        List<String> imgList = getImgData();
        int page = CommonUtils.getRandomNumber(0, imgList.size() - 1);

        FrescoUtils.loadAssetsPic(mImg,imgList.get(page));
        mImg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();

        Observable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                BaseActivity.start(WelcomeActivity.this);
                WelcomeActivity.this.finish();
            }
        });
    }

    private List<String> getImgData() {
        List<String> imgs = new ArrayList<>();
        imgs.add("bg_1.jpg");
        imgs.add("bg_2.jpg");
        imgs.add("bg_3.jpg");
        imgs.add("bg_4.jpg");
        imgs.add("bg_5.jpg");
        return imgs;
    }
}
