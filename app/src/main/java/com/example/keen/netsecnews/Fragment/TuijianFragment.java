package com.example.keen.netsecnews.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.keen.netsecnews.R;
import com.example.keen.netsecnews.adapter.DividerItemDecoration;
import com.example.keen.netsecnews.adapter.TuijianRecyclerAdapter;
import com.example.keen.netsecnews.adapter.ZixunRecyclerAdapter;
import com.example.keen.netsecnews.news.Tuijian;
import com.example.keen.netsecnews.news.Zixun;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Keen on 11/16/2016.
 *  * 位于home fragment下的子fragment
 */

public class TuijianFragment extends Fragment {

    private XRecyclerView mRecyclerView;
    private TuijianRecyclerAdapter mAdapter;
    private ImageView headerImageView;
    List<Tuijian> mDatas = new ArrayList<Tuijian>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_tuijian, container, false);
        Log.w("indicator", "Tuijian onCreateView ");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("indicator", "Tuijian onCreate ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.w("indicator", "Tuijian onActivityCreated ");

        initDatas();

        mRecyclerView = (XRecyclerView)getView().findViewById(R.id.tuijian_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mAdapter = new TuijianRecyclerAdapter(getActivity(), R.layout.item_tuijian, mDatas);
        mRecyclerView.setAdapter(mAdapter);

        //添加头部，并添加单机事件处理
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.header_item_tuijian, (ViewGroup)getView().findViewById(android.R.id.content), false);
        mRecyclerView.addHeaderView(header);
        headerImageView = (ImageView)header.findViewById(R.id.tuijian_view_header);
        //头部单机事件
        headerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "this is the tuijian image header ", Toast.LENGTH_SHORT).show();
            }
        });


        //item点击事件
        mAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position)
            {
                Toast.makeText(getActivity(), "pos = " + position, Toast.LENGTH_SHORT).show();
                //adapter.notifyItemRemoved(position);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position)
            {
                return false;
            }
        });


        //上拉，下拉刷新
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable(){
                    public void run() {

                        mDatas.clear();
                        for(int i = 0; i < 15 ;i++){
                            Tuijian mTuijian = new Tuijian();
                            mTuijian.setTuijan_id(i);
                            mTuijian.setTuijian_title("媒体揭房价上涨真相:买房杠杆高 买地杠杆更高" + Integer.toString(i));
                            mTuijian.setTuijian_digest("对崇尚安居乐业的中国人来说，房子是梦系神萦的温暖目标，房子也日渐成为经济上的沉重负担。在追踪楼市调控新政一个多月效果之时，人们同样追问，是什么推动了这一轮房价上涨？");
                            mTuijian.setTuijian_source("网易新闻");
                            mTuijian.setTuijian_comment_count(i*10);
                            mTuijian.setTuijian_publish_time("2016-11-12");
                            mTuijian.setTuijian_image_link("www.162.com");
                            mDatas.add(mTuijian);
                        }
                        mAdapter.notifyDataSetChanged();
                        mRecyclerView.refreshComplete();
                    }

                }, 1000);

            }

            @Override
            public void onLoadMore() {

                new Handler().postDelayed(new Runnable(){
                    public void run() {

                        int size = mDatas.size();
                        for(int i = 0; i < 15 ;i++){
                            Tuijian mTuijian = new Tuijian();
                            mTuijian.setTuijan_id(size + i + 1);
                            mTuijian.setTuijian_title("媒体揭房价上涨真相:买房杠杆高 买地杠杆更高" + Integer.toString(size + i + 1));
                            mTuijian.setTuijian_digest("对崇尚安居乐业的中国人来说，房子是梦系神萦的温暖目标，房子也日渐成为经济上的沉重负担。在追踪楼市调控新政一个多月效果之时，人们同样追问，是什么推动了这一轮房价上涨？");
                            mTuijian.setTuijian_source("网易新闻");
                            mTuijian.setTuijian_comment_count(i*10);
                            mTuijian.setTuijian_publish_time("2016-11-12");
                            mTuijian.setTuijian_image_link("www.162.com");
                            mDatas.add(mTuijian);
                        }
                        mRecyclerView.loadMoreComplete();
                        mAdapter.notifyDataSetChanged();
                    }
                }, 1000);

            }
        });



    }


    public void initDatas(){

        for (int i = 1; i <= 10; i++){
            Tuijian mTuijian = new Tuijian();
            mTuijian.setTuijan_id(i);
            mTuijian.setTuijian_title("媒体揭房价上涨真相:买房杠杆高 买地杠杆更高" + Integer.toString(i));
            mTuijian.setTuijian_digest("对崇尚安居乐业的中国人来说，房子是梦系神萦的温暖目标，房子也日渐成为经济上的沉重负担。在追踪楼市调控新政一个多月效果之时，人们同样追问，是什么推动了这一轮房价上涨？");
            mTuijian.setTuijian_source("网易新闻");
            mTuijian.setTuijian_comment_count(i*10);
            mTuijian.setTuijian_publish_time("2016-11-12");
            mTuijian.setTuijian_image_link("www.162.com");
            mDatas.add(mTuijian);
        }
    }


}
