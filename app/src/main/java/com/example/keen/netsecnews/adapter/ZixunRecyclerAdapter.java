package com.example.keen.netsecnews.adapter;

import android.content.Context;

import com.example.keen.netsecnews.R;
import com.example.keen.netsecnews.news.Zixun;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Keen on 11/16/2016.
 */

public class ZixunRecyclerAdapter extends CommonAdapter<Zixun> {

    public ZixunRecyclerAdapter(Context context, int layoutId, List<Zixun> datas) {

        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Zixun item, int position) {

        holder.setText(R.id.zixun_title, item.getNews_title());
        holder.setText(R.id.zixun_digest, item.getNews_digest());
        holder.setText(R.id.zixun_source, item.getNews_source());
        holder.setText(R.id.zixun_ptime, item.getNews_publish_time());
        holder.setText(R.id.zixun_commentcount, Integer.toString(item.getComment_count()) + "评论");
        holder.setImageResource(R.id.zixun_img, R.drawable.image2);

    }
}
