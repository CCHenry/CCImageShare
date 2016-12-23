/**
  * Copyright 2016 aTool.org 
  */
package com.example.henryzheng.ccimageshare.M.ZuiMeiModel;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;
import java.util.List;
/**
 * Auto-generated: 2016-12-06 16:26:42
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
@Table(name = "Image")
public class Image implements Serializable{
    @Column(name = "type" )
    private String type;
    @Column(name = "image_url")
    private String image_url;
    @Column(name = "word")
    private String word;
    @Column(name = "description")
    private String description;
    @Column(name = "tags")
    private List<String> tags;
    @Column(name = "created_at")
    private String created_at;
    @Column(name = "origin_image_url")
    private String origin_image_url;
    @Column(name = "height")
    private String height;
    @Column(name = "photo_user")
    private PhotoUser photo_user;
    @Column(name = "width")
    private String width;
    @Column(name = "viewTimes")
    private int viewTimes;
    @Column(name = "up_times")
    private int up_times;
    @Column(name = "desc_user")
    private DescUser desc_user;
    @Column(name = "publish_at")
    private String publish_at;
    @Column(name = "id" ,isId = true)
    private int id;
    @Column(name = "pub_time")
    private String pub_time;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getOrigin_image_url() {
        return origin_image_url;
    }

    public void setOrigin_image_url(String origin_image_url) {
        this.origin_image_url = origin_image_url;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public PhotoUser getPhoto_user() {
        return photo_user;
    }

    public void setPhoto_user(PhotoUser photo_user) {
        this.photo_user = photo_user;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public int getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(int viewTimes) {
        this.viewTimes = viewTimes;
    }

    public int getUp_times() {
        return up_times;
    }

    public void setUp_times(int up_times) {
        this.up_times = up_times;
    }

    public DescUser getDesc_user() {
        return desc_user;
    }

    public void setDesc_user(DescUser desc_user) {
        this.desc_user = desc_user;
    }

    public String getPublish_at() {
        return publish_at;
    }

    public void setPublish_at(String publish_at) {
        this.publish_at = publish_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPub_time() {
        return pub_time;
    }

    public void setPub_time(String pub_time) {
        this.pub_time = pub_time;
    }
}