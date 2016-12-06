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
public class Data {

    private List<Image> images;
    private boolean hasNext;
    private String baseUrl;
    public void setImages(List<Image> images) {
         this.images = images;
     }
     public List<Image> getImages() {
         return images;
     }

    public void setHasNext(boolean hasNext) {
         this.hasNext = hasNext;
     }
     public boolean getHasNext() {
         return hasNext;
     }

    public void setBaseUrl(String baseUrl) {
         this.baseUrl = baseUrl;
     }
     public String getBaseUrl() {
         return baseUrl;
     }

}