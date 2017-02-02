package com.lmm.wddog.adapter.diffCallback;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.lmm.wddog.bean.JjBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/31.
 */

public class JjDiffCallback extends DiffUtil.Callback {

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    @Override
    public int getNewListSize() {
        return 0;
    }

    @Override
    public int getOldListSize() {
        return 0;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
