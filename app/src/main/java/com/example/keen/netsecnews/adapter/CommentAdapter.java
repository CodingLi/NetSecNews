package com.example.keen.netsecnews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.keen.netsecnews.R;
import com.example.keen.netsecnews.news.Comment;
import com.example.keen.netsecnews.news.News;
import com.example.keen.netsecnews.util.ToastUtil;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Keen on 10/23/2016.
 */

public class CommentAdapter extends ArrayAdapter<Comment> {

    private  static String TAG = "CommentAdapter";
    private int resourceId;
    private List<Comment> mComment;

    public CommentAdapter(Context mContext, int viewResourceId, List<Comment> objects){
        super(mContext, viewResourceId, objects);
        this.mComment = objects;
        resourceId = viewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentViewHolder commentViewHolder;
        if(convertView == null){

            commentViewHolder = new CommentViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
            commentViewHolder.userLogo = (ImageView)convertView.findViewById(R.id.user_logo);
            commentViewHolder.userName = (TextView)convertView.findViewById(R.id.user_name);
            commentViewHolder.publishDate = (TextView)convertView.findViewById(R.id.item_public_time);
            commentViewHolder.commentIndex = (TextView)convertView.findViewById(R.id.item_index_comment);
            commentViewHolder.commentContent = (TextView)convertView.findViewById(R.id.content_text);
            commentViewHolder.loveCount = (TextView)convertView.findViewById(R.id.item_action_love);
            convertView.setTag(commentViewHolder);
        }else{
            commentViewHolder = (CommentViewHolder)convertView.getTag();
        }

        Comment comment = getItem(position);
        //设置用户名
        Log.i(TAG,comment.getUser_name()+"<-----------c----------->");
        if(comment.getUser_name() != null){
            commentViewHolder.userName.setText(comment.getUser_name());
        }else{
            commentViewHolder.userName.setText("匿名用户");
        }

        //设置用户头像
        commentViewHolder.userLogo.setImageResource(R.mipmap.girl);
        //设置内容
        commentViewHolder.commentContent.setText(comment.getComment_content());
        //设置点赞个数
        commentViewHolder.loveCount.setText(Integer.toString(comment.getLike_count()));
        //设置评论日期
        commentViewHolder.publishDate.setText(comment.getComment_date());
        //设置楼层
        commentViewHolder.commentIndex.setText((position+1) + "楼");
        //设置评论、点赞的监听事件
        commentViewHolder.loveCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.toast(getContext(), "点赞了！！！");
            }
        });
        return convertView;
    }


    public void addAll(List<Comment> mComment){
        this.mComment.addAll(0, mComment);
    }

    public void setComments(List<Comment> mComment){
        this.mComment.clear();
        this.mComment.addAll(mComment);
    }

    class CommentViewHolder{
        public ImageView userLogo;  //用户头像
        public TextView userName;  //用户名
        public TextView publishDate;  //评论时间
        public TextView commentIndex; //评论所在楼层
        public TextView loveCount; //点赞个数
        public TextView commentContent; //评论内容
        public TextView replyCount; // 回复数
    }
}
