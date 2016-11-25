package com.example.henryzheng.ccimageshare.M.Contants;

import com.example.henryzheng.ccimageshare.M.MyHelp.MyPath;

/**
 * Created by henryzheng on 2016/9/28.
 */
public class MyContonts {
    public static final String cacheDir = MyPath.getCacheDir();// 缓存总目录
    //    public static final String cacheDir= MyHelp.getCacheDir();
    public static final String smallImageCacheDir = cacheDir + "/small.jpg";
    public static final String smallImageName = "smallImage.png";
    public static final String tempDir = MyPath.getCacheDir() + "/temp";
    public static final String tempSubmitFile = tempDir + "/temp.jpg";

}
