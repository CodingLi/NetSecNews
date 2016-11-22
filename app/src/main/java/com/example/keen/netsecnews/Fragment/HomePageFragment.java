package com.example.keen.netsecnews.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.keen.netsecnews.NewsContentActivity;
import com.example.keen.netsecnews.adapter.NewsItemAdapter;
import com.example.keen.netsecnews.R;
import com.example.keen.netsecnews.news.News;
import com.example.keen.netsecnews.util.GetNewsInfo;
import com.example.keen.netsecnews.util.NetUtils;
import com.example.keen.netsecnews.util.ToastUtil;
import com.example.keen.netsecnews.view.IXListViewLoadMore;
import com.example.keen.netsecnews.view.IXListViewRefreshListener;
import com.example.keen.netsecnews.view.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keen on 2016/7/12.
 */
public class HomePageFragment extends Fragment implements IXListViewLoadMore, IXListViewRefreshListener{

    private static final int LOAD_MORE = 0x110;
    private static final int LOAD_REFRESH = 0x111;
    private static final int TIP_ERROR_NO_NETWORK = 0x112;
    private static final int TIP_ERROR_SERVER = 0x113;

    //上一次新闻列表的最后一个值（用于加载更多 做下一次查询标识）
    private  int lastNewsId = 0;
    //是否第一次进入
    private boolean isFirstIn = true;
    //是否连接网络
    private boolean isConnNet = false;
    //当前数据是否从网络加载
    private boolean isLoadingDataFromNetWork;

    public String target_url = "http://192.168.139.1:8080/NetSecWebServer/servlet/GetNewsInfo?count=10&index=";
    private String url;
    //扩展ListView
    private XListView mXListView;
    //listview适配器
    private NewsItemAdapter mNewsItemAdapter;
    //数据
    private List<News> mDatas = new ArrayList<News>();
    //网络对象
    public NetUtils netUtils =  new NetUtils();
    //获取信息类
    public GetNewsInfo getNewsInfo = new GetNewsInfo();

    public static final String ARGS_PAGE = "args_page";
    private int mPage;

    public static HomePageFragment newInstance(int page){
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE, page);
        HomePageFragment homePageFragment = new HomePageFragment();
        homePageFragment.setArguments(args);
        return homePageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
        Log.d("indicator", "onCreate mPage: " +  Integer.toString(mPage));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPage = getArguments().getInt(ARGS_PAGE);
        Log.d("indicator", Integer.toString(mPage));
        mNewsItemAdapter = new NewsItemAdapter(getActivity(), mDatas);
        //初始化
        mXListView = (XListView)getView().findViewById(R.id.xListView);
        mXListView.setAdapter(mNewsItemAdapter);
        mXListView.setPullRefreshEnable(this);
        mXListView.setPullLoadEnable(this);

        mXListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                News myNewsItem = mDatas.get(position - 1);  //因为上 拉刷新占据一个位置，所以这里获取位置时需要减去1
                Intent intent = new Intent(getActivity().getApplicationContext(), NewsContentActivity.class);
                int newsId = myNewsItem.getNews_id();
                intent.putExtra("newsId", newsId);
                startActivity(intent);
            }
        });


        if(isFirstIn){
            mXListView.startRefresh();
            isFirstIn = false;
        }else{
            mXListView.NotRefreshAtBegin();
        }
    }

    @Override
    public void onRefresh() {
        new LoadDatasTask().execute(LOAD_REFRESH);
    }

    @Override
    public void onLoadMore() {
        //-------------
        new LoadDatasTask().execute(LOAD_MORE);
    }

    /*
        加载数据的异步任务

         */
    class LoadDatasTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            switch (params[0]){
                case LOAD_MORE:
                    loadMoreData();
                    break;
                case LOAD_REFRESH:
                    return refreshData();
            }
            return -1;
        }

        @Override
        protected void onPostExecute(Integer result) {
            switch (result){
                case TIP_ERROR_NO_NETWORK:
                    ToastUtil.toast(getActivity(), "没有网络连接");
                    mNewsItemAdapter.setDatas(mDatas);
                    mNewsItemAdapter.notifyDataSetChanged();
                    break;
                case TIP_ERROR_SERVER:
                    ToastUtil.toast(getActivity(), "服务器错误");
                    break;
                default:

                    break;
            }

            mXListView.stopRefresh();
            mXListView.stopLoadMore();

        }
    }

    //会根据当前网络情况 判断是从数据库加载还是从网路继续获取
    public void loadMoreData(){
        //从网络获取数据
        if(isLoadingDataFromNetWork){
            try{
                url = target_url + Integer.toString(lastNewsId);
                List<News> newsItems = getNewsInfo.getNewsList(url);
                lastNewsId = newsItems.get(newsItems.size() - 1).getNews_id();
                //先存储到本地数据库中
                mNewsItemAdapter.addAll(newsItems);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            //从数据加载
        }

    }

    //下拉刷新数据
    //每次刷新都显示最新的前30条
    public Integer refreshData(){


        if(NetUtils.checkNet(getActivity())){
            isConnNet = true;
            //获取最新数据
            try{
                //Log.d("indicator", Integer.toString(newsType)+": "+ time[newsType]);
                // url = target_url+id+"&time="+ Base64.encodeToString(time[newsType].getBytes(), Base64.DEFAULT).trim();
                lastNewsId = 0; //每次刷新时加载最新内容
                url = target_url + Integer.toString(lastNewsId);
                //netUtils.clearAll();
                //List<News> newsItems = netUtils.getNews(url);
                List<News> newsItems = getNewsInfo.getNewsList(url);
                lastNewsId = newsItems.get(newsItems.size() - 1).getNews_id();
                mDatas.clear();
                mDatas.addAll(newsItems);
                //mAdapter.notifyDataSetInvalidated();

                isLoadingDataFromNetWork = true;
                //存入数据库
            }catch (Exception e){
                e.printStackTrace();
                isLoadingDataFromNetWork = false;
                return TIP_ERROR_SERVER;
            }
        }
        else{
            Log.d("indicator", "no network");
            isConnNet = false;
            isLoadingDataFromNetWork = false;
            //从数据库中加载

            return TIP_ERROR_NO_NETWORK;
        }

        return -1;

    }
}
