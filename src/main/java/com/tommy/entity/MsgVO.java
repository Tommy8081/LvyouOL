package com.tommy.entity;

/**
 * Created by tommy on 2020/5/10 17:10
 */

//    @ApiModel(description = "websocket消息内容")
public class MsgVO {

//        @ApiModelProperty(value = "用户id")
    private Integer userId;

//        @ApiModelProperty(value = "用户名")
    private String username;

//        @ApiModelProperty(value = "用户头像")
    private String avatar;

//        @ApiModelProperty(value = "消息")
    private String msg;

//        @ApiModelProperty(value = "在线人数")
    private int count;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "MsgVO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                '}';
    }
}
