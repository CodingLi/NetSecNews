package com.example.keen.netsecnews.adapter;

import android.content.Context;

import com.example.keen.netsecnews.R;
import com.example.keen.netsecnews.news.Tuijian;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Keen on 11/20/2016.
 */

public class TuijianRecyclerAdapter extends CommonAdapter<Tuijian> {

    public TuijianRecyclerAdapter(Context context, int layoutId, List<Tuijian> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Tuijian item, int position) {

        holder.setText(R.id.tuijian_title, item.getTuijian_title());
        holder.setText(R.id.tuijian_digest, item.getTuijian_digest());
        holder.setText(R.id.tuijian_source, item.getTuijian_source());
        holder.setText(R.id.tuijian_ptime, item.getTuijian_publish_time());
        holder.setText(R.id.tuijian_commentcount, Integer.toString(item.getTuijian_comment_count()) + "评论");
        holder.setImageResource(R.id.tuijian_img, R.drawable.image);

    }
}
