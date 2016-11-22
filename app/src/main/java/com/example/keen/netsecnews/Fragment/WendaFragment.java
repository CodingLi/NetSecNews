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
import com.example.keen.netsecnews.adapter.WendaRecyclerAdapter;
import com.example.keen.netsecnews.news.Wenda;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Keen on 11/16/2016.
 *  * 位于home fragment下的子fragment
 */

public class WendaFragment extends Fragment {

    private XRecyclerView mRecyclerView;
    private WendaRecyclerAdapter mAdapter;
    List<Wenda> mDatas = new ArrayList<Wenda>();
    private ImageView headerImageView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_wenda, container, false);
        Log.w("indicator", "Wenda onCreateView ");
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("indicator", "Wenda onCreate ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.w("indicator", "Wenda onActivityCreated ");

        initDatas();
        mRecyclerView = (XRecyclerView)getView().findViewById(R.id.wenda_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        //set adapter
        mAdapter = new WendaRecyclerAdapter(getActivity(), R.layout.item_wenda, mDatas);
        mRecyclerView.setAdapter(mAdapter);
        //添加头部，并添加单机事件处理
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.header_item_wenda, (ViewGroup)getView().findViewById(android.R.id.content), false);
        mRecyclerView.addHeaderView(header);
        headerImageView = (ImageView)header.findViewById(R.id.wenda_view_header);
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
                            Wenda mWenda = new Wenda();
                            mWenda.setWenda_title("如何有效地在海外市场推广产品？");
                            mWenda.setWenda_digest("3D打印机一直以来只能进行单向操作，任务一旦开始便无法反悔。不过最近一批研究生研发了一种新型打印机，让你在打印的同时，可以修改重塑之前的设计。让我们一起来看看这个神奇的设备究竟是怎样的吧。");
                            mWenda.setWenda_commentcount((i+1)*10);
                            mWenda.setWenda_ptime("2016-11-11");
                            mDatas.add(mWenda);
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
                            Wenda mWenda = new Wenda();
                            mWenda.setWenda_title("如何有效地在海外市场推广产品？");
                            mWenda.setWenda_digest("3D打印机一直以来只能进行单向操作，任务一旦开始便无法反悔。不过最近一批研究生研发了一种新型打印机，让你在打印的同时，可以修改重塑之前的设计。让我们一起来看看这个神奇的设备究竟是怎样的吧。");
                            mWenda.setWenda_commentcount((i+1+size)*10);
                            mWenda.setWenda_ptime("2016-11-11");
                            mDatas.add(mWenda);
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
            Wenda mWenda = new Wenda();
            mWenda.setWenda_title("如何有效地在海外市场推广产品？");
            mWenda.setWenda_digest("3D打印机一直以来只能进行单向操作，任务一旦开始便无法反悔。不过最近一批研究生研发了一种新型打印机，让你在打印的同时，可以修改重塑之前的设计。让我们一起来看看这个神奇的设备究竟是怎样的吧。");
            mWenda.setWenda_commentcount(i*10);
            mWenda.setWenda_ptime("2016-11-11");
            mDatas.add(mWenda);
        }
    }
}
