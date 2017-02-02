package com.lmm.wddog.ui.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lmm.wddog.R;

import me.yokeyword.fragmentation.SupportFragment;

import static android.view.View.GONE;

/**
 * Created by Administrator on 2017/1/14.
 */

public abstract class BaseFragment<RV extends ViewDataBinding> extends SupportFragment {
    private View mViewRoot;
    protected RV mViewBinding;
    private ViewGroup mViewGroupContent,mLoadingcontainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewRoot = inflater.inflate(R.layout.fragment_base_load,null);
        mViewBinding = DataBindingUtil.inflate(inflater,setContentLayout(),null,false);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mViewBinding.getRoot().setLayoutParams(layoutParams);
        mViewGroupContent = (ViewGroup) mViewRoot.findViewById(R.id.fb_container);
        mViewGroupContent.addView(mViewBinding.getRoot());
        return mViewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //mViewBinding.getRoot().setVisibility(View.INVISIBLE);
    }

    protected void onVisibe(){
        //mLoadingcontainer.setVisibility(GONE);
        mViewBinding.getRoot().setVisibility(View.VISIBLE);
    }

    public abstract int setContentLayout();

    protected <T extends View> T getView(int id){
        return (T)getView().findViewById(id);
    }


}
