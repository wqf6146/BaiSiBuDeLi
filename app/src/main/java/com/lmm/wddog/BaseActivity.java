package com.lmm.wddog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import com.lmm.wddog.util.StatusBarCompat;
import com.lmm.wddog.util.CommonUtils;
import com.lmm.wddog.util.SystemBarTintManager;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Administrator on 2017/1/15.
 */

public class BaseActivity extends SupportActivity  {
    private NavigationView mNavView;
    private DrawerLayout mDrawerLayout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        if (savedInstanceState==null){
            loadRootFragment(R.id.fb_container,MainFragment.getInstance());
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.top_navigation_bar);
       // StatusBarCompat.compat(this,CommonUtils.getColor(R.color.top_navigation_bar));
       // initDrawerLayout();
      //  StatusBarUtil.setColorNoTranslucentForDrawerLayout(BaseActivity.this, mDrawerLayout, CommonUtils.getColor(R.color.white));
    }

    public static void start(Context context){
        Intent intent = new Intent(context,BaseActivity.class);
        context.startActivity(intent);
    }

//    private void initDrawerLayout() {
//        mDrawerLayout = (DrawerLayout)findViewById(R.id.fb_drawerlayout);
//        mNavView = (NavigationView) findViewById(R.id.nav_view);
//        mNavView.inflateHeaderView(R.layout.nav_header_main);
//        View headerView = mNavView.getHeaderView(0);
////        LinearLayout viewById1 = (LinearLayout) headerView.findViewById(R.id.ll_header_bg);
////        viewById1.setBackground();
//        LinearLayout moonstyle = (LinearLayout) headerView.findViewById(R.id.nhm_moonstyle);
//        LinearLayout clear = (LinearLayout) headerView.findViewById(R.id.nhm_clear);
//        LinearLayout about = (LinearLayout) headerView.findViewById(R.id.nhm_about);
//        moonstyle.setOnClickListener(this);
//        clear.setOnClickListener(this);
//        about.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.nhm_moonstyle:// 夜间模式
//                //mDrawerLayout.closeDrawer(GravityCompat.START);
////                mBinding.drawerLayout.postDelayed(new Runnable() {
////                    @Override
////                    public void run() {
////                        NavHomePageActivity.startHome(MainActivity.this);
////                    }
////                }, 360);
//
//                break;
//
//            case R.id.nhm_clear://清除缓存
//
//                break;
//            case R.id.nhm_about:// 关于我
//
//                break;
//            default:
//                break;
//        }
//    }
}
