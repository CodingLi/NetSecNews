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
import com.example.keen.netsecnews.adapter.FavoriteFragmentAdapter;
import com.viewpagerindicator.TabPageIndicator;


/**
 * Created by Keen on 11/15/2016.
 */

public class FavoriteFragment extends Fragment {

    private TabPageIndicator tabPageIndicator;
    private ViewPager viewPager;
    private FavoriteFragmentAdapter adapter;

    private View rootView; // 缓冲Fragment view 防止每次Fragmenttabhost切换时，页面重新加载

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("indicator", "FavoriteFragment onCreateView");
        if(rootView == null){
            //防止切换FragmentTabhost， 重新加载
            rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
            tabPageIndicator = (TabPageIndicator)rootView.findViewById(R.id.favorite_indicator);
            viewPager = (ViewPager)rootView.findViewById(R.id.favorite_view_page);

            adapter = new FavoriteFragmentAdapter(getFragmentManager());
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
        Log.d("indicator", "FavoriteFragment onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("indicator", "FavoriteFragment onActivityCreated");
    }
}
