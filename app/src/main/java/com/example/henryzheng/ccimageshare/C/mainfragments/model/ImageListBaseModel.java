package com.example.henryzheng.ccimageshare.C.mainfragments.model;

/**
 * Created by henryzheng on 2016/12/20.
 */
public abstract class ImageListBaseModel {
   public abstract  String getUrl();
    public   String getType(){
         return getClass().getName();
     }
}
