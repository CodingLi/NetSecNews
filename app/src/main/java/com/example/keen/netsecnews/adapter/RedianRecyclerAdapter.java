package com.example.keen.netsecnews.adapter;

import android.content.Context;

import com.example.keen.netsecnews.R;
import com.example.keen.netsecnews.news.Redian;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Keen on 11/21/2016.
 */

public class RedianRecyclerAdapter extends CommonAdapter<Redian> {

    public RedianRecyclerAdapter(Context context, int layoutId, List<Redian> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Redian item, int position) {

        holder.setText(R.id.redian_title, item.getRedian_title());
        holder.setText(R.id.redian_digest, item.getRedian_digest());
        holder.setText(R.id.redian_source, item.getRedian_source());
        holder.setText(R.id.redian_ptime, item.getRedian_publish_time());
        holder.setText(R.id.redian_commentcount, Integer.toString(item.getRedian_comment_count()) + "评论");
        holder.setImageResource(R.id.redian_img, R.drawable.image3);
    }
}
