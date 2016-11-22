package com.example.henryzheng.ccimageshare.M.data;

import cn.bmob.v3.BmobObject;

/**
 * Created by henryzheng on 2016/10/12.
 */
public class ImageModel extends BmobObject {
    private String bigPicUrl;
    private String smallPicUrl;

    private String username;
    private String favorite;

    public String getBigPicUrl() {
        return bigPicUrl;
    }

    public void setBigPicUrl(String url) {
        this.bigPicUrl = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getSmallPicUrl() {
        return smallPicUrl;
    }

    public void setSmallPicUrl(String smallPicUrl) {
        this.smallPicUrl = smallPicUrl;
    }
}
