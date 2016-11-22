package com.example.keen.netsecnews.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.keen.netsecnews.R;
import com.example.keen.netsecnews.adapter.HomeFragmentAdapter;
import com.viewpagerindicator.TabPageIndicator;

/**
 * Created by keen on 2016/7/11.
 */
public class HomeFragment extends Fragment {

    private TabPageIndicator tabPageIndicator;
    private ViewPager viewPager;
    private HomeFragmentAdapter adapter;

    private View rootView; // 缓冲Fragment view 防止每次Fragmenttabhost切换时，页面重新加载

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d("indicator", "HomeFragment onCreateView");

        if(rootView == null){
            //防止切换FragmentTabhost， 重新加载
            rootView = inflater.inflate(R.layout.fragment_home, container, false);
            tabPageIndicator = (TabPageIndicator)rootView.findViewById(R.id.page_indicator);
            viewPager = (ViewPager)rootView.findViewById(R.id.view_page);

            adapter = new HomeFragmentAdapter(getFragmentManager());
            viewPager.setAdapter(adapter);
            tabPageIndicator.setViewPager(viewPager);
        }

        ViewGroup parent = (ViewGroup)rootView.getParent();
        if(parent != null){
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("indicator", "HomeFragment onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        Log.d("indicator", "HomeFragment onActivityCreated");
    }
}
