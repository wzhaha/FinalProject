package com.food.test.finalproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

/**
 * ViewPagerAdapter
 * 作用：用来管理运动，今日，我的三个碎片的适配器
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    List<String> tags = new ArrayList<>();
    private final FragmentManager fragmentManager;

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
        fragmentManager=manager;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int pageIndex) {
        //创建tag并存储到集合中
        String fragmentNameTag = makeFragmentName(container.getId(), pageIndex);
        if (!tags.contains(fragmentNameTag)) {
            tags.add(fragmentNameTag);
        }
        return super.instantiateItem(container, pageIndex);
    }

    private static String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

//    public void refreshMofifiedPageData(int position) {
//        switch (position){
//            case 0:
//                HomeFragment homeFragment=(HomeFragment)fragmentManager.findFragmentByTag(tags.get(position));
//                homeFragment.updateData();
//                break;
//        }
//    }

}
