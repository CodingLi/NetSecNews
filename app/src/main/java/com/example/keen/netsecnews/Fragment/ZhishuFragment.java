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
import com.example.keen.netsecnews.adapter.ZhishuRecyclerAdapter;
import com.example.keen.netsecnews.news.Zhishu;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Keen on 11/16/2016.
 *  * 位于home fragment下的子fragment
 */

public class ZhishuFragment extends Fragment {

    private XRecyclerView mRecyclerView;
    private ZhishuRecyclerAdapter mAdapter;
    List<Zhishu> mDatas = new ArrayList<Zhishu>();
    private ImageView headerImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_zhishu, container, false);
        Log.w("indicator", "Zhishu onCreateView ");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("indicator", "Zhishu onCreate ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.w("indicator", "Zhishu onActivityCreated ");

        initDatas();

        mRecyclerView = (XRecyclerView)getView().findViewById(R.id.zhishu_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        //set adapter
        mAdapter = new ZhishuRecyclerAdapter(getActivity(), R.layout.item_zhishu, mDatas);
        mRecyclerView.setAdapter(mAdapter);
        //添加头部，并添加单机事件处理
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.header_item_zhishu, (ViewGroup)getView().findViewById(android.R.id.content), false);
        mRecyclerView.addHeaderView(header);
        headerImageView = (ImageView)header.findViewById(R.id.zhishu_view_header);
        //头部单机事件
        headerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "this is the wenda image header ", Toast.LENGTH_SHORT).show();
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

                            Zhishu mZhishu = new Zhishu();
                            mZhishu.setZhishu_title("中国每年1200万吨粮食受重金属污染");
                            mZhishu.setZhishu_digest("首都经济贸易大学法学院教授高桂林在会上披露，环境污染也造成了严重的经济损失，我国的实际国情是要用不到世界9%的耕地养活超过22%的世界 人口，同时由于环境问题不断凸显，土地面积不断缩减");
                            mZhishu.setZhishu_commentcount((i+1)*10);
                            mZhishu.setZhishu_ptime("2016-11-11");
                            mDatas.add(mZhishu);
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

                            Zhishu mZhishu = new Zhishu();
                            mZhishu.setZhishu_title("中国每年1200万吨粮食受重金属污染");
                            mZhishu.setZhishu_digest("首都经济贸易大学法学院教授高桂林在会上披露，环境污染也造成了严重的经济损失，我国的实际国情是要用不到世界9%的耕地养活超过22%的世界 人口，同时由于环境问题不断凸显，土地面积不断缩减");
                            mZhishu.setZhishu_commentcount(i*10);
                            mZhishu.setZhishu_ptime("2016-11-11");
                            mDatas.add(mZhishu);

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
            Zhishu mZhishu = new Zhishu();
            mZhishu.setZhishu_title("中国每年1200万吨粮食受重金属污染");
            mZhishu.setZhishu_digest("首都经济贸易大学法学院教授高桂林在会上披露，环境污染也造成了严重的经济损失，我国的实际国情是要用不到世界9%的耕地养活超过22%的世界 人口，同时由于环境问题不断凸显，土地面积不断缩减");
            mZhishu.setZhishu_commentcount(i*10);
            mZhishu.setZhishu_ptime("2016-11-11");
            mDatas.add(mZhishu);
        }
    }
}
