package com.lmm.wddog.ui.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.lmm.wddog.R;
import com.lmm.wddog.adapter.provider.FoodieProvider;
import com.lmm.wddog.bean.FoodRecipe;
import com.lmm.wddog.databinding.FragmentFoodieBinding;
import com.lmm.wddog.http.ApiCallback;
import com.lmm.wddog.http.HttpUtils;
import com.lmm.wddog.util.RxUtils;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by Administrator on 2017/1/14.
 */

public class FoodieFragment extends BaseFragment<FragmentFoodieBinding> {

    private MultiTypeAdapter mAdapter;

    public static FoodieFragment getInstance() {
        FoodieFragment fragment = new FoodieFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RxUtils.addSubscription(HttpUtils.getInstance().getFoodieServer().getRecipe(0,10),
                new ApiCallback() {
                    @Override
                    public void onSuccess(Object model) {
                        setInfo((FoodRecipe)model);
                        onVisibe();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                    }
                    @Override
                    public void onFinish() {
                    }
                });
        init();
    }
    private void init() {
        mViewBinding.ffRecycleview.setPullRefreshEnabled(false);
        mViewBinding.ffRecycleview.setLoadingMoreEnabled(false);
        mViewBinding.ffRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));

        mViewBinding.ffRecycleview.setScrollBarSize(10);
        mViewBinding.ffRecycleview.setHasFixedSize(false);
        mViewBinding.ffRecycleview.setItemAnimator(new DefaultItemAnimator());
    }
    private void setInfo(FoodRecipe model){
        mAdapter = new MultiTypeAdapter();
        mAdapter.register(FoodRecipe.ResultBean.ListBean.class, new FoodieProvider());
        mAdapter.setItems(model.getResult().getList());
        mViewBinding.ffRecycleview.setAdapter(mAdapter);
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_foodie;
    }
}
