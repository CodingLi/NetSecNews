package com.example.keen.netsecnews.Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.keen.netsecnews.R;
import com.example.keen.netsecnews.adapter.HomeFragmentAdapter;
import com.viewpagerindicator.TabPageIndicator;

/**
 * Created by keen on 2016/7/12.
 */
public class testactivity extends FragmentActivity {

    private TabPageIndicator tabPageIndicator;
    private ViewPager viewPager;
    private HomeFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        tabPageIndicator = (TabPageIndicator)findViewById(R.id.page_indicator);
        viewPager = (ViewPager)findViewById(R.id.view_page);

        adapter = new HomeFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabPageIndicator.setViewPager(viewPager);
    }
}
