/**
  * Copyright 2016 aTool.org 
  */
package com.example.henryzheng.ccimageshare.M.ZuiMeiModel;

import java.io.Serializable;

/**
 * Auto-generated: 2016-12-06 16:26:42
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class DescUser implements Serializable{

    private String userName;
    private String userPhoto;
    private int userId;
    public void setUserName(String userName) {
         this.userName = userName;
     }
     public String getUserName() {
         return userName;
     }

    public void setUserPhoto(String userPhoto) {
         this.userPhoto = userPhoto;
     }
     public String getUserPhoto() {
         return userPhoto;
     }

    public void setUserId(int userId) {
         this.userId = userId;
     }
     public int getUserId() {
         return userId;
     }

}