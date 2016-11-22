package com.example.keen.netsecnews.util;

import android.util.Log;

import com.example.keen.netsecnews.news.News;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keen on 2016/6/25.
 */
public class GetNewsRunnable implements Runnable {

    private HttpUtils httpUtils;
    private Gson gson;
    private List<News> newsList;
    private News news;
    private String url;
    private NetUtils netUtils;

    public GetNewsRunnable(NetUtils netUtils, Gson gson, List<News> newsList, News news, String url){
        this.netUtils = netUtils;
        this.gson = gson;
        this.newsList = newsList;
        this.news = news;
        this.url = url;
        //netUtils = new NetUtils();
    }

    //run 方法中访问网络并解析json数据
    public void run(){

        if(newsList == null){
            newsList = new ArrayList<News>();
        }

        httpUtils = new HttpUtils();
        httpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.e("indicator", "访问成功");
                System.out.print(responseInfo.result);
                List<News> myNewsList = gson.fromJson(responseInfo.result, new TypeToken<List<News>>(){}.getType());
                netUtils.addAll(myNewsList);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.e("indicator", "访问失败");
            }
        });

    }
}
