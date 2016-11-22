package com.example.keen.netsecnews;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.keen.netsecnews.Fragment.HomePageFragment;
import com.example.keen.netsecnews.adapter.CommentAdapter;
import com.example.keen.netsecnews.news.Comment;
import com.example.keen.netsecnews.util.GetNewsInfo;
import com.example.keen.netsecnews.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by keen on 2016/7/14.
 */
public class NewsContentActivity extends AppCompatActivity {
    private WebView newsContentWebView;
    private ListView listView;
    private List<Comment> mComments = new ArrayList<Comment>();
    //获取信息类
    public GetNewsInfo getNewsInfo = new GetNewsInfo();
    CommentAdapter commentAdapter;
    //http://192.168.139.1:8080/NetSecWebServer/servlet/GetNewsInfo?newsid=1
    private final String web_site = "http://192.168.139.1:8080/NetSecWebServer/servlet/GetNewsInfo?newsid=";
    private final String comment_url = "http://192.168.139.1:8080/NetSecWebServer/servlet/GetNewsInfo?count=10&commentindex=";
    //private String web_url;
    Toolbar toolbar;
    String newsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_newscontent);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("新闻中心");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.img_menu);

        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        newsId = getIntent().getExtras().get("newsId").toString();



        newsContentWebView = (WebView)findViewById(R.id.webview);
        newsContentWebView.getSettings().setJavaScriptEnabled(true);
        newsContentWebView.getSettings().setUseWideViewPort(true);
        newsContentWebView.getSettings().setLoadWithOverviewMode(true);
        newsContentWebView.setVerticalScrollBarEnabled(true);
        newsContentWebView.setHorizontalFadingEdgeEnabled(true);
//        newsContentWebView.setWebViewClient(new WebViewClient(){
//            @Override
//            public void onPageFinished(WebView view, String url) {
//
//                commentAdapter = new CommentAdapter(NewsContentActivity.this, R.layout.comment_item, mComments);
//                listView = (ListView)findViewById(R.id.comment_list);
//                listView.setAdapter(commentAdapter);
//                setListViewHeightBasedOnChildren(listView);
//            }
//        });

        listView = (ListView)findViewById(R.id.comment_list);
        new LoadNewsTask().execute(1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_newscontent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.action_share:
                showShare();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    //shareSdk....
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.ssdk_oks_share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }


    /***
     * 动态设置listview的高度 item 总布局必须是linearLayout
     *
     * @param listView
     */
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1))
                + 15;
        listView.setLayoutParams(params);
    }



    class LoadNewsTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            List<Comment> commentList = getNewsInfo.GetComment(comment_url + newsId);
            mComments.clear();
            mComments.addAll(commentList);
            return -1;
        }

        @Override
        protected void onPostExecute(Integer result) {

            //commentAdapter.setComments(mComments);
            //commentAdapter.notifyDataSetChanged();
            newsContentWebView.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageFinished(WebView view, String url) {

                    commentAdapter = new CommentAdapter(NewsContentActivity.this, R.layout.comment_item, mComments);
                    listView.setAdapter(commentAdapter);
                    setListViewHeightBasedOnChildren(listView);

                }
            });
            newsContentWebView.loadUrl(web_site + newsId);

        }
    }
}
