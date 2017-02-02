package com.lmm.wddog.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lmm.wddog.R;
import com.lmm.wddog.ui.fragment.Foodie2RecipeFragment;
import com.lmm.wddog.util.StatusBarCompat;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Administrator on 2017/1/31.
 */

public class FoodierActivity extends SupportActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodier);

        Foodie2RecipeFragment fragment = Foodie2RecipeFragment.getInstance();
        fragment.getArguments().putInt(Foodie2RecipeFragment.RECIPE_MENU_ID,
                getIntent().getIntExtra(Foodie2RecipeFragment.RECIPE_MENU_ID,-1));
        loadRootFragment(R.id.af_fragment_container, fragment);
    }

    public static void start(Context mContext, int tag) {
        Intent intent = new Intent(mContext, FoodierActivity.class);
        intent.putExtra(Foodie2RecipeFragment.RECIPE_MENU_ID, tag);
        mContext.startActivity(intent);
    }
}
