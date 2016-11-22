package com.example.keen.netsecnews.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.keen.netsecnews.Fragment.FavoriteWendaFragment;
import com.example.keen.netsecnews.Fragment.FavoriteZhishuFragment;
import com.example.keen.netsecnews.Fragment.FavoriteZixunFragment;

/**
 * Created by Keen on 11/22/2016.
 */

public class FavoriteFragmentAdapter extends FragmentPagerAdapter {

    private String[] favorite_titles = new String[]{"资讯", "指数", "问答"};

    public FavoriteFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("indicator", "getItem :" + Integer.toString(position));
//        return HomePageFragment.newInstance(position + 1);
        //创建子fragment
        return  getSubFragment(position);
    }

    @Override
    public int getCount() {
        return favorite_titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return favorite_titles[position];
    }


    public Fragment getSubFragment(int position){
        Fragment fragment = null;
        Bundle args = null;
        //根据indicator位置返回 对应的fragment
        switch (position){
            case 0:
                //新建一个ZixunFragment来展示ViewPager item的内容，并传递参数
                fragment = new FavoriteZixunFragment();
                args = new Bundle();
                args.putString("arg", favorite_titles[position]);
                fragment.setArguments(args);
                break;
            case 1:
                fragment = new FavoriteZhishuFragment();
                args = new Bundle();
                args.putString("arg", favorite_titles[position]);
                fragment.setArguments(args);
                break;
            case 2:
                fragment = new FavoriteWendaFragment();
                args = new Bundle();
                args.putString("arg", favorite_titles[position]);
                fragment.setArguments(args);
                break;
            default:
                fragment = new FavoriteZixunFragment();
                args = new Bundle();
                args.putString("arg", favorite_titles[position]);
                fragment.setArguments(args);
                break;
        }
        return fragment;
    }
}
