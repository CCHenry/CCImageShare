package com.example.henryzheng.ccimageshare.M.utils;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;

/**
 * Created by henryzheng on 2016/11/9.
 */
public class CCFileUtil {
    /**
     * 通过uri返回真实路径
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getPathFromUrl(Activity context, Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = context.managedQuery(uri, proj, null, null, null);
        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualimagecursor.moveToFirst();
        String img_path = actualimagecursor.getString(actual_image_column_index);
        return img_path;
    }

    public static String getHandleFilePath(String path) {
        File file = new File(path);
        if (file.exists()) {

        } else {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    public static String getHandleDirPath(String path) {
        File file = new File(path);
        if (file.exists()) {

        } else {
            file.mkdirs();
        }
        return path;
    }
}
