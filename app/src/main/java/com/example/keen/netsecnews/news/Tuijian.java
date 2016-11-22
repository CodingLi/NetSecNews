package com.example.keen.netsecnews.news;

/**
 * Created by Keen on 11/20/2016.
 */

public class Tuijian {

    //新闻内容id
    private int tuijan_id;
    //新闻标题
    private String tuijian_title;
    //新闻摘要
    private String tuijian_digest;
    //图片地址
    private String tuijian_image_link;
    //新闻来源
    private String tuijian_source;
    //新闻时间
    private String tuijian_publish_time;
    //评论数量
    private int tuijian_comment_count;

    public int getTuijan_id() {
        return tuijan_id;
    }

    public void setTuijan_id(int tuijan_id) {
        this.tuijan_id = tuijan_id;
    }

    public String getTuijian_title() {
        return tuijian_title;
    }

    public void setTuijian_title(String tuijian_title) {
        this.tuijian_title = tuijian_title;
    }

    public String getTuijian_digest() {
        return tuijian_digest;
    }

    public void setTuijian_digest(String tuijian_digest) {
        this.tuijian_digest = tuijian_digest;
    }

    public String getTuijian_image_link() {
        return tuijian_image_link;
    }

    public void setTuijian_image_link(String tuijian_image_link) {
        this.tuijian_image_link = tuijian_image_link;
    }

    public String getTuijian_source() {
        return tuijian_source;
    }

    public void setTuijian_source(String tuijian_source) {
        this.tuijian_source = tuijian_source;
    }

    public String getTuijian_publish_time() {
        return tuijian_publish_time;
    }

    public void setTuijian_publish_time(String tuijian_publish_time) {
        this.tuijian_publish_time = tuijian_publish_time;
    }

    public int getTuijian_comment_count() {
        return tuijian_comment_count;
    }

    public void setTuijian_comment_count(int tuijian_comment_count) {
        this.tuijian_comment_count = tuijian_comment_count;
    }
}
