package com.lmm.wddog.adapter.provider;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lmm.wddog.R;
import com.lmm.wddog.adapter.base.NestFullListViewAdapter;
import com.lmm.wddog.adapter.base.NestFullViewHolder;
import com.lmm.wddog.bean.JjBean;
import com.lmm.wddog.databinding.ItemJjImgBinding;
import com.lmm.wddog.event.FragmentJumpEvent;
import com.lmm.wddog.http.imagemodel.ImageViewModel;
import com.lmm.wddog.ui.fragment.GifDetailFragment;
import com.lmm.wddog.ui.fragment.SubsamplingScaleFragment;

import org.greenrobot.eventbus.EventBus;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/1/22.
 */

public class JjViewProvider extends ItemViewProvider<JjBean.ListBean,JjViewProvider.Holder>{

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, @NonNull JjBean.ListBean object) {
        holder.mViewBinding.setData(object);
        holder.mViewBinding.setImageload(new ImageViewModel());

        NestFullListViewAdapter adapter = holder.mViewBinding.ijNestlistview.getAdapter();
        if (adapter==null){
            holder.mViewBinding.ijNestlistview.setAdapter(new NestFullListViewAdapter<JjBean.ListBean.TopCommentsBean>
                    (R.layout.item_jj_comment,object.getTop_comments()) {
                @Override
                public void onBind(int pos, JjBean.ListBean.TopCommentsBean data, NestFullViewHolder holder) {
                    holder.setText(R.id.ijc_godname,String.format(
                            holder.getConvertView().getResources().getString(R.string.jjcommentname),data.getU().getName()));
                    holder.setText(R.id.ijc_desc,data.getContent());
                }
            });
        }else {
            adapter.setDatas(object.getTop_comments());
            holder.mViewBinding.ijNestlistview.updateUI();
        }
    }

    @NonNull
    @Override
    protected Holder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new Holder(DataBindingUtil.inflate(inflater, R.layout.item_jj_img,parent,false).getRoot());
    }

    static class Holder extends RecyclerView.ViewHolder{
        ItemJjImgBinding mViewBinding;



        private Holder(@NonNull View itemView){
           super(itemView);
            mViewBinding = DataBindingUtil.getBinding(this.itemView);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JjBean.ListBean bean = mViewBinding.getData();
                    if (bean.getType().equals("image")){
                       SubsamplingScaleFragment fragment = SubsamplingScaleFragment.getInstance();
                        fragment.getArguments().putString(SubsamplingScaleFragment.URL,
                                bean.getImage().getBig().get(0));
                        EventBus.getDefault().post(new FragmentJumpEvent(fragment));

                    }else{
                        GifDetailFragment fragment = GifDetailFragment.getInstance();
                        fragment.getArguments().putParcelable(GifDetailFragment.GIF,
                               bean);
                        EventBus.getDefault().post(new FragmentJumpEvent(fragment));
                    }
                }
            });
       }
   }
}
