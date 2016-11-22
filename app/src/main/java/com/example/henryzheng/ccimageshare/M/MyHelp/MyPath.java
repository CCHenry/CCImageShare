package com.example.henryzheng.ccimageshare.M.MyHelp;

import android.os.Environment;

/**
 * Created by henryzheng on 2016/11/10.
 */
public class MyPath {
    /**
     * 缓存总目录
     * @return
     */
    public static String getCacheDir() {
        if (isSDCARDMounted()) {
            return Environment.getExternalStorageDirectory() + "/XutilsImageApp";
        } else
            return null;
    }

    public static String getSmallPicSavePath(){
        return getCacheDir()+"/"+"smallPicSave";
    }
    private static boolean isSDCARDMounted() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

}
