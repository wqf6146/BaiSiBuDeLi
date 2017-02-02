package com.lmm.wddog.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.lmm.wddog.R;
import com.lmm.wddog.databinding.FragmentSubsamplingscaleBinding;

public class SubsamplingScaleFragment extends BaseFragment<FragmentSubsamplingscaleBinding> {

    public static SubsamplingScaleFragment getInstance() {
        SubsamplingScaleFragment fragment = new SubsamplingScaleFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static String URL = "URL";

    @Override
    public int setContentLayout() {
        return R.layout.fragment_subsamplingscale;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewBinding.fsMBigImage.showImage(Uri.parse(getArguments().getString(URL)));
    }

}
