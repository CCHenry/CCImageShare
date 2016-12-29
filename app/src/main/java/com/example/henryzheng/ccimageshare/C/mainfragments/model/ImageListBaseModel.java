package com.example.henryzheng.ccimageshare.C.mainfragments.model;

import java.io.Serializable;

/**
 * Created by henryzheng on 2016/12/20.
 */
public abstract class ImageListBaseModel implements Serializable {

    public void setUrl(String url){}
    public abstract  String getUrl();

    public   String getType(){
         return getClass().getName();
     }
}
