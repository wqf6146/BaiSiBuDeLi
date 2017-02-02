package com.lmm.wddog.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lmm.wddog.R;
import com.lmm.wddog.bean.JjBean;
import com.lmm.wddog.databinding.FragmentGifDetailBinding;
import com.lmm.wddog.databinding.FragmentSubsamplingscaleBinding;


/**
 * Created by 06peng on 2015/6/25.
 */
public class GifDetailFragment extends BaseFragment<FragmentGifDetailBinding> {

    public static GifDetailFragment getInstance() {
        GifDetailFragment fragment = new GifDetailFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static String GIF = "GIF";

    @Override
    public int setContentLayout() {
        return R.layout.fragment_gif_detail;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewBinding.setUrl(getArguments().getString(GIF));
    }

}
