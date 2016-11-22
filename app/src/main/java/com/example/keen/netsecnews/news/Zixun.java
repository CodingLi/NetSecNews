package com.example.keen.netsecnews.news;

/**
 * Created by keen on 2016/7/13.
 */
public class Zixun {

    //新闻内容id
    private int news_id;
    //新闻标题
    private String news_title;
    //新闻摘要
    private String news_digest;
    //图片地址
    private String news_image_link;
    //新闻来源
    private String news_source;
    //新闻时间
    private String news_publish_time;
    //评论数量
    private int comment_count;

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_digest() {
        return news_digest;
    }

    public void setNews_digest(String news_digest) {
        this.news_digest = news_digest;
    }

    public String getNews_image_link() {
        return news_image_link;
    }

    public void setNews_image_link(String news_image_link) {
        this.news_image_link = news_image_link;
    }

    public String getNews_source() {
        return news_source;
    }

    public void setNews_source(String news_source) {
        this.news_source = news_source;
    }

    public String getNews_publish_time() {
        return news_publish_time;
    }

    public void setNews_publish_time(String news_publish_time) {
        this.news_publish_time = news_publish_time;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }
}

