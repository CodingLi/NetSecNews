package com.example.keen.netsecnews.adapter;

import android.content.Context;

import com.example.keen.netsecnews.R;
import com.example.keen.netsecnews.news.Wenda;
import com.example.keen.netsecnews.news.Zhishu;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Keen on 11/21/2016.
 */

public class ZhishuRecyclerAdapter extends CommonAdapter<Zhishu> {


    public ZhishuRecyclerAdapter(Context context, int layoutId, List<Zhishu> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Zhishu item, int position) {
        holder.setText(R.id.zhishu_title, item.getZhishu_title());
        holder.setText(R.id.zhishu_digest, item.getZhishu_digest());
        holder.setText(R.id.zhishu_ptime, item.getZhishu_ptime());
        holder.setText(R.id.zhishu_commentcount, Integer.toString(item.getZhishu_commentcount()) + "评论");
    }
}
