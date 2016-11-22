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
import com.example.keen.netsecnews.adapter.RedianRecyclerAdapter;
import com.example.keen.netsecnews.adapter.TuijianRecyclerAdapter;
import com.example.keen.netsecnews.news.Redian;
import com.example.keen.netsecnews.news.Tuijian;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Keen on 11/16/2016.
 *  * 位于home fragment下的子fragment
 */

public class RedianFragment extends Fragment {

    private XRecyclerView mRecyclerView;
    private RedianRecyclerAdapter mAdapter;
    private ImageView headerImageView;
    List<Redian> mDatas = new ArrayList<Redian>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_redian, container, false);
        Log.w("indicator", "Redian onCreateView ");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("indicator", "Redian onCreate ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.w("indicator", "Redian onActivityCreated ");

        initDatas();

        mRecyclerView = (XRecyclerView)getView().findViewById(R.id.redian_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mAdapter = new RedianRecyclerAdapter(getActivity(), R.layout.item_redian, mDatas);
        mRecyclerView.setAdapter(mAdapter);

        //添加头部，并添加单机事件处理
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.header_item_redian, (ViewGroup)getView().findViewById(android.R.id.content), false);
        mRecyclerView.addHeaderView(header);
        headerImageView = (ImageView)header.findViewById(R.id.redian_view_header);
        //头部单机事件
        headerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "this is the redian image header ", Toast.LENGTH_SHORT).show();
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
                            Redian mRedian = new Redian();
                            mRedian.setRedian_id(i);
                            mRedian.setRedian_title("北京八达岭动物园虎园重新开放 伤人虎放回园区" + Integer.toString(i));
                            mRedian.setRedian_digest("法制晚报快讯（记者董振杰编辑王贺健）发生老虎伤人事件后，八达岭野生动物园的东北虎园一直处于关闭状态。今天上午，《法制晚报》记者探访发现，东北虎园经过改造后已重新开放");
                            mRedian.setRedian_source("网易新闻");
                            mRedian.setRedian_comment_count(i*10);
                            mRedian.setRedian_publish_time("2016-11-12");
                            mRedian.setRedian_image_link("www.163.com");
                            mDatas.add(mRedian);
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
                            Redian mRedian = new Redian();
                            mRedian.setRedian_id(size + i + 1);
                            mRedian.setRedian_title("北京八达岭动物园虎园重新开放 伤人虎放回园区" + Integer.toString(size + i + 1));
                            mRedian.setRedian_digest("法制晚报快讯（记者董振杰编辑王贺健）发生老虎伤人事件后，八达岭野生动物园的东北虎园一直处于关闭状态。今天上午，《法制晚报》记者探访发现，东北虎园经过改造后已重新开放");
                            mRedian.setRedian_source("网易新闻");
                            mRedian.setRedian_comment_count(i*10);
                            mRedian.setRedian_publish_time("2016-11-12");
                            mRedian.setRedian_image_link("www.163.com");
                            mDatas.add(mRedian);
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
            Redian mRedian = new Redian();
            mRedian.setRedian_id(i);
            mRedian.setRedian_title("北京八达岭动物园虎园重新开放 伤人虎放回园区" + Integer.toString(i));
            mRedian.setRedian_digest("法制晚报快讯（记者董振杰编辑王贺健）发生老虎伤人事件后，八达岭野生动物园的东北虎园一直处于关闭状态。今天上午，《法制晚报》记者探访发现，东北虎园经过改造后已重新开放");
            mRedian.setRedian_source("网易新闻");
            mRedian.setRedian_comment_count(i*10);
            mRedian.setRedian_publish_time("2016-11-12");
            mRedian.setRedian_image_link("www.163.com");
            mDatas.add(mRedian);
        }
    }
}
