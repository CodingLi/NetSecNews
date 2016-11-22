package com.example.keen.netsecnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.keen.netsecnews.R;
import com.example.keen.netsecnews.news.News;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * Created by keen on 2016/7/13.
 */
public class NewsItemAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<News> mDatas;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

    public NewsItemAdapter(Context context, List<News> datas){
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);

        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        options = new DisplayImageOptions.Builder().showStubImage(R.drawable.get_image_error)
                .showImageForEmptyUri(R.drawable.get_image_error).showImageOnFail(R.drawable.get_image_error).cacheInMemory()
                .cacheOnDisc().displayer(new RoundedBitmapDisplayer(20)).displayer(new FadeInBitmapDisplayer(300)).build();
    }

    public void addAll(List<News> mDatas){
        this.mDatas.addAll(mDatas);
    }

    public void setDatas(List<News> mDatas){
        this.mDatas.clear();
        this.mDatas.addAll(mDatas);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(getCount() == 0){
            return mInflater.inflate(R.layout.list_item, null);
        }

        ViewHolder holder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();

            holder.mDigest = (TextView)convertView.findViewById(R.id.id_digest);
            holder.mTitle = (TextView)convertView.findViewById(R.id.id_title);
            holder.mPTime = (TextView)convertView.findViewById(R.id.id_ptime);
            holder.mImg = (ImageView)convertView.findViewById(R.id.id_newsImg);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        News newsItem = mDatas.get(position);
        holder.mTitle.setText(newsItem.getNews_title());
        holder.mDigest.setText(newsItem.getNews_digest());
        holder.mPTime.setText(newsItem.getNews_publish_time());

        //for image test
        //holder.mImg.setImageResource(R.drawable.image);

        if(newsItem.getNews_image_link() != null){
            holder.mImg.setVisibility(View.VISIBLE);
            imageLoader.displayImage(newsItem.getNews_image_link(), holder.mImg, options);
        }else{
            holder.mImg.setVisibility(View.GONE);
        }

        return convertView;
    }



    private final class ViewHolder{
        TextView mTitle;
        TextView mDigest;
        ImageView mImg;
        TextView mPTime;
    }
}
