package com.lmm.wddog.adapter.provider;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lmm.wddog.R;
import com.lmm.wddog.bean.FoodSecondRecipe;
import com.lmm.wddog.databinding.ItemFoodieSecondrecipeBinding;
import com.lmm.wddog.event.FragmentJumpEvent;
import com.lmm.wddog.http.imagemodel.ImageViewModel;
import com.lmm.wddog.ui.fragment.FoodieRecipeDetailFragment;

import org.greenrobot.eventbus.EventBus;

import me.drakeet.multitype.ItemViewProvider;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017/1/26.
 */

public class FoodieSecondProvider extends ItemViewProvider<FoodSecondRecipe.ResultBean.RecipesBean,FoodieSecondProvider.Holder> {

    private SupportFragment mFragment;

    public FoodieSecondProvider(SupportFragment fragment){
        mFragment = fragment;
    }
    @NonNull
    @Override
    protected Holder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new Holder(DataBindingUtil.inflate(inflater, R.layout.item_foodie_secondrecipe,parent,false).getRoot(),mFragment);
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, @NonNull FoodSecondRecipe.ResultBean.RecipesBean recipesBean) {
        holder.mViewBinding.setDatabean(recipesBean);
        holder.mViewBinding.setImgload(new ImageViewModel());
    }

    static class Holder extends RecyclerView.ViewHolder{
        ItemFoodieSecondrecipeBinding mViewBinding;
        SupportFragment mFragment;
        public Holder(View view,SupportFragment fragment){
            super(view);
            mViewBinding = DataBindingUtil.getBinding(this.itemView);
            mFragment = fragment;
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FoodieRecipeDetailFragment fragment = FoodieRecipeDetailFragment.getInstance();
                    fragment.getArguments().putParcelable(FoodieRecipeDetailFragment.RECIPE_DATEAL,
                            mViewBinding.getDatabean());
                    mFragment.start(fragment);
                }
            });
        }
    }
}
