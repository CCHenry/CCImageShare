/**
  * Copyright 2016 aTool.org 
  */
package com.example.henryzheng.ccimageshare.M.ZuiMeiModel;

import java.util.List;
/**
 * Auto-generated: 2016-12-06 16:26:42
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Image {

    private String image_url;
    private String word;
    private String description;
    private List<String> tags;
    private String created_at;
    private String origin_image_url;
    private String height;
    private PhotoUser photo_user;
    private String width;
    private int viewTimes;
    private int up_times;
    private DescUser desc_user;
    private String publish_at;
    private int id;
    public void setImageUrl(String imageUrl) {
         this.image_url = imageUrl;
     }
     public String getImageUrl() {
         return image_url;
     }

    public void setWord(String word) {
         this.word = word;
     }
     public String getWord() {
         return word;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setTags(List<String> tags) {
         this.tags = tags;
     }
     public List<String> getTags() {
         return tags;
     }

    public void setCreatedAt(String createdAt) {
         this.created_at = createdAt;
     }
     public String getCreatedAt() {
         return created_at;
     }

    public void setOriginImageUrl(String originImageUrl) {
         this.origin_image_url = originImageUrl;
     }
     public String getOriginImageUrl() {
         return origin_image_url;
     }

    public void setHeight(String height) {
         this.height = height;
     }
     public String getHeight() {
         return height;
     }

    public void setPhotoUser(PhotoUser photoUser) {
         this.photo_user = photoUser;
     }
     public PhotoUser getPhotoUser() {
         return photo_user;
     }

    public void setWidth(String width) {
         this.width = width;
     }
     public String getWidth() {
         return width;
     }

    public void setViewTimes(int viewTimes) {
         this.viewTimes = viewTimes;
     }
     public int getViewTimes() {
         return viewTimes;
     }

    public void setUpTimes(int upTimes) {
         this.up_times = upTimes;
     }
     public int getUpTimes() {
         return up_times;
     }

    public void setDescUser(DescUser descUser) {
         this.desc_user = descUser;
     }
     public DescUser getDescUser() {
         return desc_user;
     }

    public void setPublishAt(String publishAt) {
         this.publish_at = publishAt;
     }
     public String getPublishAt() {
         return publish_at;
     }

    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

}