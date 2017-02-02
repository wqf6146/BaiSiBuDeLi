package com.lmm.wddog;

import android.app.Application;
import android.widget.Toast;

import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.loader.fresco.FrescoImageLoader;
import com.lmm.wddog.bean.JjBean;
import com.lmm.wddog.http.ApiCallback;
import com.lmm.wddog.http.HttpUtils;
import com.lmm.wddog.util.CheckNetwork;
import com.lmm.wddog.util.RxUtils;
import com.lmm.wddog.util.fresco.ImagePipelineConfigFactory;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Administrator on 2017/1/14.
 */

public class App extends Application {
    private static App mApp;

    public static App newInstance(){
        return mApp;
    }

    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        mRefWatcher = LeakCanary.install(this);
        mApp = this;
        BigImageViewer.initialize(FrescoImageLoader.with(this, ImagePipelineConfigFactory.getImagePipelineConfig(this),null));
//        Fresco.initialize(this, ImagePipelineConfigFactory.getImagePipelineConfig(this));
        if (!CheckNetwork.isNetworkConnected(this) && !CheckNetwork.isWifiConnected(this))
            Toast.makeText(this, "无网络连接,请检查你的网络!", Toast.LENGTH_LONG).show();

        initData();
    }

    private void initData() {
        RxUtils.addSubscription(HttpUtils.getInstance().getJjServer().getJjPicContent(),
                new ApiCallback<JjBean>() {
                    @Override
                    public void onSuccess(JjBean model) {
                    }

                    @Override
                    public void onFailure(int code, String msg) {

                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }
}
