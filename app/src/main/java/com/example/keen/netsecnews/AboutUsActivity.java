package com.example.keen.netsecnews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by keen on 2016/7/21.
 * 用户设置下的  关于我们 活动
 */
public class AboutUsActivity extends AppCompatActivity {

    Toolbar aboutus_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        aboutus_toolbar = (Toolbar)findViewById(R.id.aboutus_toolbar);
        aboutus_toolbar.setTitle("");
        setSupportActionBar(aboutus_toolbar);
        aboutus_toolbar.setNavigationIcon(R.drawable.img_menu);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_about_us, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
