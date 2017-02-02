package com.lmm.wddog.http.api;

import com.lmm.wddog.bean.FoodRecipe;
import com.lmm.wddog.bean.FoodSecondRecipe;
import com.lmm.wddog.bean.JjBean;
import com.lmm.wddog.bean.Jj_8_Bean;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/14.
 */

public interface JjApi {
    String Jj_URL = "http://s.budejie.com";

    String Jj_D_URL = "http://d.api.budejie.com";
    //http://d.api.budejie.com/topic/list/chuanyue/10/budejie-android-6.6.4/0-8.json?market=360zhushou&udid=353490060521594&appname=baisibudejie&os=4.4.4&client=android&visiting=&mac=8c%3A3a%3Ae3%3A95%3A5c%3Af8&ver=6.6.4

    @GET("/topic/list/chuanyue/10/budejie-android-6.6.4/0-8.json")
    Observable<JjBean> getJj8PicContent();

//    @POST("/recipe/menus/{tag}/{size}")
    //Observable<FoodRecipe> getRecipe(@Path("tag") int tag,@Path("size") int size);
    @GET("/topic/list/jingxuan/10/budejie-android-6.6.4/0-20.json")
    Observable<JjBean> getJjPicContent();

    @GET("/topic/list/jingxuan/10/budejie-android-6.6.4/{time}")
    Observable<JjBean> getJjMoreContent(@Path("time") String time);
}
