package com.lmm.wddog.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.xrecyclerview.XRecyclerView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.lmm.wddog.R;
import com.lmm.wddog.adapter.JjListAdapter;
import com.lmm.wddog.bean.JjBean;
import com.lmm.wddog.databinding.FragmentJjBinding;
import com.lmm.wddog.http.ApiCallback;
import com.lmm.wddog.http.HttpUtils;
import com.lmm.wddog.util.RxUtils;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Created by Administrator on 2017/1/16.
 */

public class JjFragment extends BaseFragment<FragmentJjBinding> {

    private JjListAdapter mAdapter;

    private XRecyclerView mRecycleView;

    private int mNextPage;

    public static JjFragment getInstance() {
        JjFragment fragment = new JjFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {

        mRecycleView = mViewBinding.fjRecycleview;
        mRecycleView.setPullRefreshEnabled(true);
        mRecycleView.setLoadingMoreEnabled(true);
        //mRecycleView.addHeaderView(new YunRefreshHeader(getContext()));
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        // 需加，不然滑动不流畅
        //mRecycleView.setNestedScrollingEnabled(false);
        mRecycleView.setHasFixedSize(false);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
       // mRecycleView.setVerticalScrollBarEnabled(true);
       //mRecycleView.setScrollBarSize(10);
        mRecycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                RxUtils.addSubscription(HttpUtils.getInstance().getJj8Server().getJj8PicContent(), new ApiCallback() {
                    @Override
                    public void onSuccess(Object model) {
                        JjBean bean = (JjBean)model;
                        Toast.makeText(getContext(), "更新了"+bean.getInfo().getCount()+"内容", Toast.LENGTH_SHORT).show();
                        mAdapter.addAllatBegin(bean.getList());
                        mAdapter.notifyDataSetChanged();
                        mRecycleView.refreshComplete();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        Log.e(JjFragment.class.getName(),code + ":" + msg);
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                        mRecycleView.refreshComplete();
                    }

                    @Override
                    public void onFinish() {
                        mRecycleView.refreshComplete();
                    }
                });
            }

            @Override
            public void onLoadMore() {
                RxUtils.addSubscription(HttpUtils.getInstance().getJjServer().getJjMoreContent(
                        mNextPage + "-20.json"), new ApiCallback<JjBean>() {
                    @Override
                    public void onSuccess(JjBean model) {
                        mNextPage = model.getInfo().getNp();
                        mAdapter.addAll(model.getList());
                        mAdapter.notifyDataSetChanged();
                        mRecycleView.refreshComplete();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        Log.e(JjFragment.class.getName(),code + ":" + msg);
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                        mRecycleView.refreshComplete();
                    }

                    @Override
                    public void onFinish() {
                        mRecycleView.refreshComplete();
                    }
                });

            }
        });

        mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == SCROLL_STATE_IDLE)
                    Fresco.getImagePipeline().resume();
                else
                    Fresco.getImagePipeline().pause();
            }
        });

        RxUtils.addSubscription(HttpUtils.getInstance().getJjServer().getJjPicContent(),
                new ApiCallback<JjBean>() {
                    @Override
                    public void onSuccess(JjBean model) {
                        setInfo(model);
                        mNextPage = model.getInfo().getNp();
                        //onVisibe();
                    }

                    @Override
                    public void onFailure(int code, String msg) {

                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }

    private void setInfo(JjBean bean) {
        mAdapter = new JjListAdapter();
        //mAdapter.register(JjBean.ListBean.class, new JjViewProvider());
       // mAdapter.setItems(bean.getList());
        mAdapter.addAll(bean.getList());
        mRecycleView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_jj;
    }
}
