package com.lmm.wddog.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.lmm.wddog.R;
import com.lmm.wddog.adapter.provider.HealthProvider;
import com.lmm.wddog.bean.HealthBean;
import com.lmm.wddog.databinding.FragmentHealthBinding;
import com.lmm.wddog.http.ApiCallback;
import com.lmm.wddog.http.HttpUtils;
import com.lmm.wddog.util.RxUtils;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by Administrator on 2017/1/23.
 */

public class HealthFragment extends BaseFragment<FragmentHealthBinding> {

    private MultiTypeAdapter mAdapter;

    public static HealthFragment getInstance() {
        HealthFragment fragment = new HealthFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewBinding.fhRecycleview.setPullRefreshEnabled(false);
        mViewBinding.fhRecycleview.setLoadingMoreEnabled(false);
        mViewBinding.fhRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        // 需加，不然滑动不流畅
        //mViewBinding.fhRecycleview.setNestedScrollingEnabled(false);
        mViewBinding.fhRecycleview.setHasFixedSize(false);
        mViewBinding.fhRecycleview.setScrollBarSize(10);
        mViewBinding.fhRecycleview.setItemAnimator(new DefaultItemAnimator());

        RxUtils.addSubscription(HttpUtils.getInstance().getHealthServer().getHealthList(
                20), new ApiCallback() {
            @Override
            public void onSuccess(Object model) {
                setUiInfo((HealthBean)model);
            }

            @Override
            public void onFailure(int code, String msg) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    private void setUiInfo(HealthBean bean) {
        mAdapter = new MultiTypeAdapter();
        mAdapter.register(HealthBean.ResultBean.class, new HealthProvider());
        mAdapter.setItems(bean.getResult());

        mViewBinding.fhRecycleview.setAdapter(mAdapter);
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_health;
    }


}
