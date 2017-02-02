package com.lmm.wddog.adapter.provider;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lmm.wddog.R;
import com.lmm.wddog.bean.FoodRecipe;
import com.lmm.wddog.databinding.ItemFoodieImgBinding;
import com.lmm.wddog.event.FragmentJumpEvent;
import com.lmm.wddog.http.imagemodel.ImageViewModel;
import com.lmm.wddog.ui.activity.FoodierActivity;
import com.lmm.wddog.ui.fragment.Foodie2RecipeFragment;

import org.greenrobot.eventbus.EventBus;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/1/26.
 */

public class FoodieProvider extends ItemViewProvider<FoodRecipe.ResultBean.ListBean,FoodieProvider.Holder> {

    @NonNull
    @Override
    protected Holder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new Holder(DataBindingUtil.inflate(inflater, R.layout.item_foodie_img,parent,false).getRoot());
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, @NonNull FoodRecipe.ResultBean.ListBean listBean) {
        holder.mViewBinding.setDatabean(listBean);
        holder.mViewBinding.setImgdata(new ImageViewModel());
    }

    static class Holder extends RecyclerView.ViewHolder {
        ItemFoodieImgBinding mViewBinding;
        public Holder(View view){
            super(view);
            mViewBinding = DataBindingUtil.getBinding(this.itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Foodie2RecipeFragment fragment = Foodie2RecipeFragment.getInstance();
//                    fragment.getArguments().putInt(Foodie2RecipeFragment.RECIPE_MENU_ID,
//                            mViewBinding.getDatabean().getM().getId());
//                    EventBus.getDefault().post(new FragmentJumpEvent(fragment));

                    FoodierActivity.start(itemView.getContext(),mViewBinding.getDatabean().getM().getId());
                }
            });
        }
    }
}
