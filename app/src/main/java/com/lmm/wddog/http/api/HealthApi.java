package com.lmm.wddog.http.api;

import com.lmm.wddog.bean.HealthBean;
import com.lmm.wddog.bean.JjBean;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/23.
 */

public interface HealthApi {
    //http://apis.guokr.com/handpick/v2/article.json?
    // retrieve_type=by_offset&limit=20&ad=1&category=health
    String Healt_URL = "http://apis.guokr.com";



    //    @POST("/recipe/menus/{tag}/{size}")
    //Observable<FoodRecipe> getRecipe(@Path("tag") int tag,@Path("size") int size);
    @GET("/handpick/v2/article.json?retrieve_type=by_offset&category=health")
    Observable<HealthBean> getHealthList(@Query("limit") int limit);
}