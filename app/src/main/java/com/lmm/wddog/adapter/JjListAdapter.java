package com.lmm.wddog.adapter;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.imagepipeline.request.BasePostprocessor;

import com.lmm.wddog.R;
import com.lmm.wddog.adapter.base.BaseRecyclerViewAdapter;
import com.lmm.wddog.adapter.base.BaseRecyclerViewHolder;
import com.lmm.wddog.adapter.base.NestFullListViewAdapter;
import com.lmm.wddog.adapter.base.NestFullViewHolder;
import com.lmm.wddog.bean.JjBean;
import com.lmm.wddog.databinding.ItemJjImgBinding;
import com.lmm.wddog.databinding.ItemJjLargeBinding;
import com.lmm.wddog.event.FragmentJumpEvent;
import com.lmm.wddog.http.imagemodel.ImageViewModel;
import com.lmm.wddog.ui.fragment.GifDetailFragment;
import com.lmm.wddog.ui.fragment.SubsamplingScaleFragment;

import org.greenrobot.eventbus.EventBus;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017/1/27.
 */

public class JjListAdapter extends BaseRecyclerViewAdapter<JjBean.ListBean> {

    private static int LARGE = 0x1;
    private static int NORMAL = 0x2;

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseRecyclerViewHolder holder = null;
        if (viewType == LARGE)
            holder = new LargeHolder(parent, R.layout.item_jj_large);
        else
            holder = new NormalHolder(parent,R.layout.item_jj_img);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        int res = NORMAL;
        JjBean.ListBean bean = getData().get(position);
        if (bean.getType().equals("image")){
            res = bean.getImage().getHeight() > 3000 ? LARGE : NORMAL;
        }
        return res;
    }

    private class NormalHolder extends BaseRecyclerViewHolder<JjBean.ListBean,ItemJjImgBinding>{
        public NormalHolder(ViewGroup parent, int layoutid){
            super(parent,layoutid);
            binding.largeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JjBean.ListBean object = binding.getData();
                    boolean bGif = object.getType().equals("gif");
                    String url = bGif ?
                            object.getGif().getImages().get(0) : object.getImage().getBig().get(0);
                    SupportFragment fragment = null;
                    if ( bGif ){
                        fragment = GifDetailFragment.getInstance();
                        fragment.getArguments().putString(GifDetailFragment.GIF,url);
                    }else{
                        fragment = SubsamplingScaleFragment.getInstance();
                        fragment.getArguments().putString(SubsamplingScaleFragment.URL,url);
                    }
                    EventBus.getDefault().post(new FragmentJumpEvent(fragment));
                }
            });
        }

        @Override
        public void onBindViewHolder(final JjBean.ListBean object, int position) {
            binding.setData(object);
            binding.setImageload(new ImageViewModel());

            NestFullListViewAdapter adapter = binding.ijNestlistview.getAdapter();
            if (adapter==null){
                binding.ijNestlistview.setAdapter(new NestFullListViewAdapter<JjBean.ListBean.TopCommentsBean>
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
                binding.ijNestlistview.updateUI();
            }
        }
    }

    private class LargeHolder extends BaseRecyclerViewHolder<JjBean.ListBean,ItemJjLargeBinding>{
        public LargeHolder(ViewGroup parent, int layoutid){
            super(parent,layoutid);
            binding.jgMBigImage.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            binding.ijContentlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SubsamplingScaleFragment fragment = SubsamplingScaleFragment.getInstance();
                    fragment.getArguments().putString(SubsamplingScaleFragment.URL, binding.getData().getImage().getBig().get(0));
                    EventBus.getDefault().post(new FragmentJumpEvent(fragment));
                }
            });

        }

        @Override
        public void onBindViewHolder(final JjBean.ListBean object, final int position) {
            binding.setData(object);
            binding.setImageload(new ImageViewModel());

            NestFullListViewAdapter adapter = binding.ijNestlistview.getAdapter();
            if (adapter==null){
                binding.ijNestlistview.setAdapter(new NestFullListViewAdapter<JjBean.ListBean.TopCommentsBean>
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
                binding.ijNestlistview.updateUI();
            }
        }
    }
}
