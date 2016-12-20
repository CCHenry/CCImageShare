package com.example.henryzheng.ccimageshare.C.mainfragments.model;

/**
 * Created by henryzheng on 2016/12/20.
 */
public class ZuiMeiBestModel extends  ImageListBaseModel{
    @Override
    public  String getUrl() {
        return  "http://lab.zuimeia.com/photo/photography/list/?appVersion=2.6" +
                ".3&channel=wallpaper&imsi=460012202301362&systemVersion=23&resolution=1080x1920" +
                "&platform=android&package_name=com.brixd" +
                ".wallpager&page=%d&tag=0&lang=zh-cn&openUDID=862258036210848&page_size=30&timestamp" +
                "=1480672294308";

    }


}
