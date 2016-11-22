package com.example.keen.netsecnews;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.keen.netsecnews.Fragment.FavoriteFragment;
import com.example.keen.netsecnews.Fragment.UserFragment;
import com.example.keen.netsecnews.Fragment.HomeFragment;
import com.example.keen.netsecnews.Fragment.VideoFragment;

import android.widget.TabHost.TabSpec;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private FragmentTabHost fragmentTabHost;
    private String texts[] = {"首页", "视频", "收藏", "我的"};
    private int imageButton[] = {
            R.drawable.bt_home_selector, R.drawable.bt_video_selector, R.drawable.bt_favorite_selector, R.drawable.bt_me_selector
    };
    private Class fragmentArray[] = {
            HomeFragment.class, VideoFragment.class, FavoriteFragment.class, UserFragment.class
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.w("indicator", "initTabHost ok...");
        initTabHost();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    //-------------------------------------------------------------
    //init tabhost
    private void initTabHost(){
        fragmentTabHost = (FragmentTabHost)findViewById(R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.tabcontent);
        //去掉分割线
        fragmentTabHost.getTabWidget().setDividerDrawable(null);
        //fragmentTabHost.setBackground(R.drawable.tap_border);

        for (int i = 0; i < texts.length; i++){
            TabSpec spec = fragmentTabHost.newTabSpec(texts[i]).setIndicator(getView(i));
            fragmentTabHost.addTab(spec, fragmentArray[i], null);
        }

    }

    private View getView(int i){

        View view = View.inflate(MainActivity.this, R.layout.view_tab_indicator, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.tab_iv_image);
        TextView textView = (TextView)view.findViewById(R.id.tab_iv_text);

        imageView.setImageResource(imageButton[i]);
        textView.setText(texts[i]);

        return view;
    }





}
