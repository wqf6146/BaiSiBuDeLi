package com.lmm.wddog;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lmm.wddog.adapter.MainFragmentAdapter;
import com.lmm.wddog.databinding.FragmentMainBinding;
import com.lmm.wddog.event.FragmentJumpEvent;
import com.lmm.wddog.ui.fragment.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainFragment extends BaseFragment<FragmentMainBinding> {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MainFragmentAdapter mTabAdapter;

    public static MainFragment getInstance() {
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFragmentJumpEvent(FragmentJumpEvent event) {
        start(event.fragment);
    }

    private void init() {


        ((AppCompatActivity)getActivity()).setSupportActionBar(mViewBinding.toolBar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTabLayout = mViewBinding.tabLayout;
        mViewPager = mViewBinding.viewpager;
        mTabAdapter = new MainFragmentAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mTabAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);
//        for (int i=0;i<mTabLayout.getTabCount();i++){
//            mTabLayout.getTabAt(i).setCustomView(mTabAdapter.getTabView(i));
//        }
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //mTabAdapter.setTabClickSelect((ImageView)tab.getCustomView(),tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //mTabAdapter.setTabClickUnselected((ImageView)tab.getCustomView(),tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //StatusBarUtil.setColor(getActivity(), CommonUtils.getColor(R.color.top_navigation_bar),0);
    }
}
