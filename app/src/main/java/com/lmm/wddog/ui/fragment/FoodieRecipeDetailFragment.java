package com.lmm.wddog.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lmm.wddog.R;
import com.lmm.wddog.adapter.base.NestFullListViewAdapter;
import com.lmm.wddog.adapter.base.NestFullViewHolder;
import com.lmm.wddog.bean.FoodSecondRecipe;
import com.lmm.wddog.databinding.FragmentRecipeDetailBinding;
import com.lmm.wddog.bean.FoodSecondRecipe.ResultBean.RecipesBean.MajorBean;
import com.lmm.wddog.bean.FoodSecondRecipe.ResultBean.RecipesBean.CookstepBean;
/**
 * Created by Administrator on 2017/1/15.
 */

public class FoodieRecipeDetailFragment extends BaseFragment<FragmentRecipeDetailBinding> {

    public static String RECIPE_DATEAL = "RECIPE_DATEAL";

    public static FoodieRecipeDetailFragment getInstance() {
        FoodieRecipeDetailFragment fragment = new FoodieRecipeDetailFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FoodSecondRecipe.ResultBean.RecipesBean recipesBean = getArguments().getParcelable(RECIPE_DATEAL);
        mViewBinding.setData(recipesBean);
        init(recipesBean);
    }

    private void init(FoodSecondRecipe.ResultBean.RecipesBean recipesBean) {
      //  Glide.with(this).load(recipesBean.getP()).into(mViewBinding.frdBgImg);
        mViewBinding.frdBgImg.setImageURI(Uri.parse(recipesBean.getP()));
        //Glide.with(this).load(recipesBean.getA().getP()).into(mViewBinding.irdMidpart.irdGodimg);
        mViewBinding.irdMidpart.irdGodimg.setImageURI(Uri.parse(recipesBean.getA().getP()));



        mViewBinding.irdContentpart.irdmMaterialList.setAdapter(
                new NestFullListViewAdapter<MajorBean>(R.layout.item_recipe_material, recipesBean.getMajor()) {
                    @Override
                    public void onBind(int pos, MajorBean bean, NestFullViewHolder holder) {
                        holder.setText(R.id.irm_desc,bean.getTitle());
                    }
                });
        mViewBinding.irdContentpart.irdmStepList.setAdapter(
                new NestFullListViewAdapter<CookstepBean>(R.layout.item_recipe_step,recipesBean.getCookstep()){
                    @Override
                    public void onBind(int pos, CookstepBean bean, NestFullViewHolder holder) {
                        ((SimpleDraweeView)holder.getView(R.id.irs_step_img)).setImageURI(Uri.parse(bean.getImage()));
                        holder.setText(R.id.irs_step_numb,String.format(getContext().getResources().getString(R.string.recipestep),bean.getPosition()));
                        holder.setText(R.id.irs_step_desc,bean.getContent());
                    }
                });
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_recipe_detail;
    }
}
