package com.example.henryzheng.ccimageshare.C.mainfragments.model;

/**
 * Created by henryzheng on 2016/12/20.
 */
public class TodayZuiMeiModel extends  ImageListBaseModel{
    @Override
    public String getUrl() {
        return   "http://lab.zuimeia.com/wallpaper/category/1/?appVersion=2.6" +
                ".3&channel=wallpaper&imsi=460012202301362&systemVersion=23&resolution=1080x1920" +
                "&platform=android&req_version_code=2&package_name=com.brixd" +
                ".wallpager&time=%s&lang=zh-cn&openUDID=862258036210848&page_size=30";
    }


}
