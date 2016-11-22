package com.example.keen.netsecnews.adapter;

import android.content.Context;

import com.example.keen.netsecnews.Fragment.HomePageFragment;
import com.example.keen.netsecnews.R;
import com.example.keen.netsecnews.news.Wenda;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Keen on 11/19/2016.
 */

public class WendaRecyclerAdapter extends CommonAdapter<Wenda> {

    public WendaRecyclerAdapter(Context context, int layoutId, List<Wenda> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Wenda item, int position) {
        holder.setText(R.id.wenda_title, item.getWenda_title());
        holder.setText(R.id.wenda_digest, item.getWenda_digest());
        holder.setText(R.id.wenda_ptime, item.getWenda_ptime());
        holder.setText(R.id.wenda_commentcount, Integer.toString(item.getWenda_commentcount()) + "评论");
    }
}
