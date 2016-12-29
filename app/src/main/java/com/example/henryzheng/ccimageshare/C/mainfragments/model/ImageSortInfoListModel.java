package com.example.henryzheng.ccimageshare.C.mainfragments.model;

/**
 * Created by henryzheng on 2016/12/29.
 */
public class ImageSortInfoListModel extends ImageListBaseModel {
    String url;

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
