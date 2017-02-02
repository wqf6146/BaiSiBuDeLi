package com.lmm.wddog.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.lmm.wddog.R;
import com.lmm.wddog.adapter.provider.FoodieSecondProvider;
import com.lmm.wddog.bean.FoodSecondRecipe;
import com.lmm.wddog.databinding.FragmentSecondrecipeBinding;
import com.lmm.wddog.http.ApiCallback;
import com.lmm.wddog.http.HttpUtils;
import com.lmm.wddog.util.RxUtils;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by Administrator on 2017/1/15.
 */

public class Foodie2RecipeFragment extends BaseFragment<FragmentSecondrecipeBinding>{

    public static String RECIPE_MENU_ID = "RECIPE_MENU_ID";

    private int mMenuId;
    private MultiTypeAdapter mAdapter;

    public static Foodie2RecipeFragment getInstance() {
        Foodie2RecipeFragment fragment = new Foodie2RecipeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        mMenuId = getArguments().getInt(RECIPE_MENU_ID,-1);
        if (mMenuId == -1){
            Log.e("Foodie2RecipeFragment","menuid error");
        }
        RxUtils.addSubscription(HttpUtils.getInstance().getFoodieServer().getSecondRecipe(mMenuId,0,10),
                new ApiCallback() {
                    @Override
                    public void onSuccess(Object model) {
                        setInfo((FoodSecondRecipe)model);
                        onVisibe();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                    }
                    @Override
                    public void onFinish() {
                    }
                });
    }

    private void init() {
        mViewBinding.fsRecycleview.setPullRefreshEnabled(false);
        mViewBinding.fsRecycleview.setLoadingMoreEnabled(false);
//        if (mHeaderView == null) {
//            mHeaderView = mHeaderBinding.getRoot();
//            bindingView.xrvEveryday.addHeaderView(mHeaderView);
//        }
//        if (mFooterView == null) {
//            mFooterBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.footer_item_everyday, null, false);
//            mFooterView = mFooterBinding.getRoot();
//            bindingView.xrvEveryday.addFootView(mFooterView, true);
//            bindingView.xrvEveryday.noMoreLoading();
//        }
        mViewBinding.fsRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        // 需加，不然滑动不流畅
        mViewBinding.fsRecycleview.setNestedScrollingEnabled(false);
        mViewBinding.fsRecycleview.setScrollBarSize(10);
        mViewBinding.fsRecycleview.setHasFixedSize(false);
        mViewBinding.fsRecycleview.setItemAnimator(new DefaultItemAnimator());
    }

    private void setInfo(FoodSecondRecipe model) {
//        if (mAdapter == null){
//            mAdapter = new FoodieSecondListAdapter(getContext());
//            mAdapter.setOnItemClickListener(new OnItemClickListener<FoodSecondRecipe.ResultBean.RecipesBean>() {
//                 @Override
//                public void onClick(FoodSecondRecipe.ResultBean.RecipesBean recipesBean, int position) {
//                    FoodieRecipeDetailFragment fragment = FoodieRecipeDetailFragment.getInstance();
//                    fragment.getArguments().putParcelable(FoodieRecipeDetailFragment.RECIPE_DATEAL,
//                            recipesBean);
//                    start(fragment);
//                }
//            });
//        }
//        mAdapter.addAll(model.getResult().getRecipes());
//        mViewBinding.fsRecycleview.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
        mAdapter = new MultiTypeAdapter();
        mAdapter.register(FoodSecondRecipe.ResultBean.RecipesBean.class,new FoodieSecondProvider(this));
        mAdapter.setItems(model.getResult().getRecipes());
        mViewBinding.fsRecycleview.setAdapter(mAdapter);
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_secondrecipe;
    }
}
