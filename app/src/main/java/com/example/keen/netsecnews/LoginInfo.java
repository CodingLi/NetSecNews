package com.example.keen.netsecnews;

/**
 * Created by keen on 2016/7/22.
 */
public class LoginInfo {

    private String flag;
    private String username;
    //private String UserId;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        token = token;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        username = userName;
    }

//    public String getUserId() {
//        return UserId;
//    }
//
//    public void setUserId(String userId) {
//        UserId = userId;
//    }

}
