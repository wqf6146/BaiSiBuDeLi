package com.lmm.wddog.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lmm.wddog.App;
import com.lmm.wddog.R;
import com.lmm.wddog.ui.fragment.FoodieFragment;
import com.lmm.wddog.ui.fragment.HealthFragment;
import com.lmm.wddog.ui.fragment.JjFragment;
import com.lmm.wddog.util.CommonUtils;

/**
 * Created by Administrator on 2017/1/14.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter{
    private static int[] PIC = new int[]{
            R.drawable.fun,
            R.drawable.run,
            R.drawable.food
    };

    private static int[] PIC_1 = new int[]{
            R.drawable.fun1,
            R.drawable.run1,
            R.drawable.food1
    };

    private static String[] titles = new String[]{
            "搞笑","健康","美食"
    };

    private static int JJ = 0;
    private static int Foodie = 2;
    private static int HEALTH = 1;

    public MainFragmentAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == JJ){
            fragment = JjFragment.getInstance();
        }else if (position == Foodie){
            fragment = FoodieFragment.getInstance();
        }else if (position == HEALTH){
            fragment = HealthFragment.getInstance();
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public View getTabView(int position){
        ImageView im = new ImageView(App.newInstance());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(CommonUtils.dip2px(30f),
                CommonUtils.dip2px(30f));
        im.setLayoutParams(lp);
        im.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        im.setImageResource(PIC[position]);

        //默认选择1
        if (position==0)
            im.setImageResource(PIC_1[position]);
        return im;
    }
    public void setTabClickSelect(ImageView tabIm,int position){
        tabIm.setImageResource(PIC_1[position]);
    }

    public void setTabClickUnselected(ImageView tabIm,int position){
        tabIm.setImageResource(PIC[position]);
    }
}
