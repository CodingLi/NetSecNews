package com.example.keen.netsecnews.util;

import android.util.Log;

import com.example.keen.netsecnews.news.Comment;
import com.example.keen.netsecnews.news.News;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;

import java.util.List;

/**
 * Created by Keen on 10/23/2016.
 */

public class GetNewsInfo {

    private  static String NEWS_TAG = "GetNewsInfo";
    private HttpUtils httpUtils;
    private Gson gson;

    public GetNewsInfo(){
        gson = new Gson();
        httpUtils =  new HttpUtils();
        httpUtils.configCurrentHttpCacheExpiry(1000*10);
    }


//获得新闻列表信息
    public List<News> getNewsList(String url){

//        RequestParams params = new RequestParams();
//        params.addQueryStringParameter("userkey", userkey );
//        params.addQueryStringParameter("str", str );
//        params.addQueryStringParameter("sign", sign );

        try{
            ResponseStream responseStream = httpUtils.sendSync(HttpMethod.GET, url, null);
            String retStr = responseStream.readString().toString();
            List<News> newsList = gson.fromJson(retStr, new TypeToken<List<News>>(){}.getType());
            return newsList;

        }catch (Exception e){
            LogUtils.e(e.getMessage(), e);
            return null;
        }
    }
//获取评论信息
    //不允许在主线程中执行
    public List<Comment> GetComment(String url){

        try{
            ResponseStream responseStream = httpUtils.sendSync(HttpMethod.GET, url, null);
            String retStr = responseStream.readString();
            List<Comment> commentsList = gson.fromJson(retStr, new TypeToken<List<Comment>>(){}.getType());
            return commentsList;

        }catch (Exception e){
            Log.d(NEWS_TAG, e.getMessage());
            LogUtils.e(e.getMessage(), e);
            return null;
        }
    }

}
