package com.huadiao.pojo.systemSpecific;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 12 22 12:32
 * <p>
 * 用户所有信息
 */
@JSONType(orders = {"uid", "user_id", "username", "before_username", "update_username_date", "login_time", "register_time", "log_off", "log_off_date"})
public class UserAllInfos {
    private String uid;
    private String user_id;
    private String username;
    private String before_username;
    private String update_username_date;
    private String login_time;
    private String register_time;
    private String log_off;
    private String log_off_date;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBefore_username() {
        return before_username;
    }

    public void setBefore_username(String before_username) {
        this.before_username = before_username;
    }

    public String getUpdate_username_date() {
        return update_username_date;
    }

    public void setUpdate_username_date(String update_username_date) {
        this.update_username_date = update_username_date;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    public String getLog_off() {
        return log_off;
    }

    public void setLog_off(String log_off) {
        this.log_off = log_off;
    }

    public String getLog_off_date() {
        return log_off_date;
    }

    public void setLog_off_date(String log_off_date) {
        this.log_off_date = log_off_date;
    }

    @Override
    public String toString() {
        return "UserAllInfos{" +
                "uid='" + uid + '\'' +
                ", user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", before_username='" + before_username + '\'' +
                ", update_username_date='" + update_username_date + '\'' +
                ", login_time='" + login_time + '\'' +
                ", register_time='" + register_time + '\'' +
                ", log_off='" + log_off + '\'' +
                ", log_off_date='" + log_off_date + '\'' +
                '}';
    }
}
