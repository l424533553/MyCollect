package com.collect.user_luo.mycollect.activity.eventbus;

/**
 * 说明：
 * 作者：User_luo on 2018/7/2 11:17
 * 邮箱：424533553@qq.com
 */
public class MessageEvent {

    public String name;
    public String password;

    public MessageEvent(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
