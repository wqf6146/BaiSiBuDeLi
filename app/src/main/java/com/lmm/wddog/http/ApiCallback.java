package com.lmm.wddog.http;


import android.util.Log;

import com.lmm.wddog.bean.HttpResult;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public abstract class ApiCallback<M> extends Subscriber<M> {

    public abstract void onSuccess(M model);

    public abstract void onFailure(int code, String msg);

    public abstract void onFinish();


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            String msg = httpException.getMessage();
            Log.e("httperr:","code=" + code);
            if (code == 504) {
                msg = "网络不给力";
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试";
            }
            onFailure(code, msg);
        } else {
            onFailure(0, e.getMessage());
        }
        onFinish();
    }

    @Override
    public void onNext(M model) {
//        if ( ((HttpResult) model).getCode() != 200 ){
//            throw new ApiException(((HttpResult) model).getMsg());
//        }
        onSuccess(model);
    }

    @Override
    public void onCompleted() {
        onFinish();
    }
}
