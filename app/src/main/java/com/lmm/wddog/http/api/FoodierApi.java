package com.lmm.wddog.http.api;

import com.lmm.wddog.bean.FoodRecipe;
import com.lmm.wddog.bean.FoodSecondRecipe;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;

import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/14.
 */

public interface FoodierApi {
    String FOOD_URL = "http://api.douguo.net";


//    @POST("/recipe/menus/{tag}/{size}")
    //Observable<FoodRecipe> getRecipe(@Path("tag") int tag,@Path("size") int size);
    @POST("/recipe/menus/{tag}/{size}")
    Observable<FoodRecipe> getRecipe(@Path("tag") int tag, @Path("size") int size);

    /**
     *
     * url:http://api.douguo.net/menu/recipes/4798122/0/10
     */
    @Headers("version:636.4")
    @POST("/menu/recipes/{id}/{tag}/{size}")
    Observable<FoodSecondRecipe> getSecondRecipe(@Path("id") int id,@Path("tag") int tag,@Path("size") int size);
}
