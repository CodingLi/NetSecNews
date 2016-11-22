package com.example.keen.netsecnews.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.keen.netsecnews.Fragment.HomePageFragment;
import com.example.keen.netsecnews.Fragment.RedianFragment;
import com.example.keen.netsecnews.Fragment.TuijianFragment;
import com.example.keen.netsecnews.Fragment.WendaFragment;
import com.example.keen.netsecnews.Fragment.ZhishuFragment;
import com.example.keen.netsecnews.Fragment.ZixunFragment;

import java.util.List;

/**
 * Created by keen on 2016/7/11.
 */
//tab 页面 适配器
public class HomeFragmentAdapter extends FragmentPagerAdapter {

    private String[] titles = new String[]{"资讯", "推荐", "热点", "指数", "问答"};

    public HomeFragmentAdapter(FragmentManager fm){
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
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public Fragment getSubFragment(int position){
        Fragment fragment = null;
        Bundle args = null;
        //根据indicator位置返回 对应的fragment
        switch (position){
            case 0:
                //新建一个ZixunFragment来展示ViewPager item的内容，并传递参数
                fragment = new ZixunFragment();
                args = new Bundle();
                args.putString("arg", titles[position]);
                fragment.setArguments(args);
                break;
            case 1:
                fragment = new TuijianFragment();
                args = new Bundle();
                args.putString("arg", titles[position]);
                fragment.setArguments(args);
                break;
            case 2:
                fragment = new RedianFragment();
                args = new Bundle();
                args.putString("arg", titles[position]);
                fragment.setArguments(args);
                break;
            case 3:
                fragment = new ZhishuFragment();
                args = new Bundle();
                args.putString("arg", titles[position]);
                fragment.setArguments(args);
                break;
            case 4:
                fragment = new WendaFragment();
                args = new Bundle();
                args.putString("arg", titles[position]);
                fragment.setArguments(args);
                break;
            default:
                fragment = new ZixunFragment();
                args = new Bundle();
                args.putString("arg", titles[position]);
                fragment.setArguments(args);
                break;
        }
        return fragment;
    }
}
