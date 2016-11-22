package com.example.keen.netsecnews.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.keen.netsecnews.news.News;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keen on 2016/6/25.
 */
public class NetUtils {

   // private HttpUtils httpUtils;
    private Gson gson = new Gson();
    private List<News> newsList = new ArrayList<News>();

    private News news;


    //获取新闻
    public List<News> getNews(String url){

        GetNewsRunnable getNewsRunnable = new GetNewsRunnable(this, gson, newsList, news, url);
        getNewsRunnable.run();
        return newsList;
    }

//    public List<News> getFavorutes(String username, String token, String url){
//
//        HttpUtils httpUtils = new HttpUtils();
//        RequestParams params = new RequestParams();
//        params.addBodyParameter("username", username);
//        params.addBodyParameter("token", token);
//        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>(){
//            @Override
//            public void onSuccess(ResponseInfo<String> responseInfo) {
//                Log.e("indicator", "访问成功");
//                System.out.print(responseInfo.result);
//                List<News> myNewsList = gson.fromJson(responseInfo.result, new TypeToken<List<News>>(){}.getType());
//            }
//
//            @Override
//            public void onFailure(HttpException e, String s) {
//                Log.e("indicator", "访问失败");
//            }
//        });
//    }

    public void addAll(List<News> newsList){
        if(newsList != null){
            clearAll();
            this.newsList.addAll(newsList);
        }

    }

    public void clearAll(){
        newsList.clear();
    }




    /**
     * 检查当前手机网络
     *
     * @param context
     * @return
     */
    public static boolean checkNet(Context context)
    {
        // 判断连接方式
//        boolean wifiConnected = isWIFIConnected(context);
//        boolean mobileConnected = isMOBILEConnected(context);
//        if (wifiConnected == false && mobileConnected == false)
//        {
//            // 如果都没有连接返回false，提示用户当前没有网络
//            return false;
//        }
        return true;
    }

    // 判断手机使用是wifi还是mobile
    /**
     * 判断手机是否采用wifi连接
     */
    public static boolean isWIFIConnected(Context context)
    {
        // Context.CONNECTIVITY_SERVICE).

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo != null && networkInfo.isConnected())
        {
            return true;
        }
        return false;
    }

    public static boolean isMOBILEConnected(Context context)
    {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (networkInfo != null && networkInfo.isConnected())
        {
            return true;
        }
        return false;
    }

}
