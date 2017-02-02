package com.lmm.wddog.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/1/20.
 */

public abstract class SimpleAdapter<D,V extends ViewDataBinding> extends BaseAdapter {
    private V mBindingView;
    private int mItemLayoutId;
    private List<D> mListData;

    public SimpleAdapter(int itemLayoutId,List<D> data){
        mListData = data;
        mItemLayoutId = itemLayoutId;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                    ,mItemLayoutId,parent,false).getRoot();
            mBindingView = DataBindingUtil.getBinding(convertView);
        }
        setInfo(mBindingView,mListData.get(position));
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    public abstract void setInfo(V viewbind,D bean);

}
