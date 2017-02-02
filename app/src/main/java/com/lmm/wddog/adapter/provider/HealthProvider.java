package com.lmm.wddog.adapter.provider;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lmm.wddog.R;
import com.lmm.wddog.bean.HealthBean;
import com.lmm.wddog.databinding.ItemHealthBinding;
import com.lmm.wddog.http.imagemodel.ImageViewModel;
import com.lmm.wddog.ui.activity.WebViewActivity;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/1/23.
 */

public class HealthProvider extends ItemViewProvider<HealthBean.ResultBean,HealthProvider.Holder> {

    @NonNull
    @Override
    protected Holder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new Holder(DataBindingUtil.inflate(inflater, R.layout.item_health,parent,false).getRoot());
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, @NonNull HealthBean.ResultBean bean) {
        holder.mViewBinding.setData(bean);
        holder.mViewBinding.setImgdata(new ImageViewModel());
    }

    static class Holder extends RecyclerView.ViewHolder {

        ItemHealthBinding mViewBinding;

        public Holder(View view){
            super(view);
            mViewBinding = DataBindingUtil.getBinding(this.itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HealthBean.ResultBean bean = mViewBinding.getData();
                    WebViewActivity.loadUrl(itemView.getContext(),bean.getPage_source(),
                            bean.getTitle());
                }
            });
        }
    }
}
