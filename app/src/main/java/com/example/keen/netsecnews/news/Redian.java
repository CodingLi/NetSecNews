package com.example.keen.netsecnews.news;

/**
 * Created by Keen on 11/21/2016.
 */

public class Redian {
    //新闻内容id
    private int redian_id;
    //新闻标题
    private String redian_title;
    //新闻摘要
    private String redian_digest;
    //图片地址
    private String redian_image_link;
    //新闻来源
    private String redian_source;
    //新闻时间
    private String redian_publish_time;
    //评论数量
    private int redian_comment_count;

    public int getRedian_id() {
        return redian_id;
    }

    public void setRedian_id(int redian_id) {
        this.redian_id = redian_id;
    }

    public String getRedian_title() {
        return redian_title;
    }

    public void setRedian_title(String redian_title) {
        this.redian_title = redian_title;
    }

    public String getRedian_digest() {
        return redian_digest;
    }

    public void setRedian_digest(String redian_digest) {
        this.redian_digest = redian_digest;
    }

    public String getRedian_image_link() {
        return redian_image_link;
    }

    public void setRedian_image_link(String redian_image_link) {
        this.redian_image_link = redian_image_link;
    }

    public String getRedian_source() {
        return redian_source;
    }

    public void setRedian_source(String redian_source) {
        this.redian_source = redian_source;
    }

    public String getRedian_publish_time() {
        return redian_publish_time;
    }

    public void setRedian_publish_time(String redian_publish_time) {
        this.redian_publish_time = redian_publish_time;
    }

    public int getRedian_comment_count() {
        return redian_comment_count;
    }

    public void setRedian_comment_count(int redian_comment_count) {
        this.redian_comment_count = redian_comment_count;
    }
}
