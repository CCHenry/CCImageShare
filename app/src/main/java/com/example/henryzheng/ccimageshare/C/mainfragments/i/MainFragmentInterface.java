package com.example.henryzheng.ccimageshare.C.mainfragments.i;

import com.example.henryzheng.ccimageshare.M.ZuiMeiModel.Image;

import java.util.List;

/**
 * Created by henryzheng on 2016/12/20.
 */
public interface MainFragmentInterface {
    public  void loadNewImages( List<Image> images);

    public  void refreshImages( List<Image> images);
}
