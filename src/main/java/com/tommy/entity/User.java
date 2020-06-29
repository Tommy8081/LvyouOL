package com.tommy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tommy on 2020/4/12 17:32
 */
//@Entity
//@Table(name = "t_user")
public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    private String uname;
    private String tel;
    private String email;
    private String avatar;
    private String password;
    private boolean ifadmin;
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_date;
    @Temporal(TemporalType.TIMESTAMP)
    private Date update_date;

//    @OneToMany(mappedBy = "user")
//    private List<Post> posts = new ArrayList<>();
//
//    public List<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(List<Post> posts) {
//        this.posts = posts;
//    }

    public User() {
    }

    public Integer getUid() {
        return uid;
    }

    public String getUname() {
        return uname;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPassword() {
        return password;
    }

    public boolean isIfadmin() {
        return ifadmin;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreate_date() {
        return create_date;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getUpdate_date() {
        return update_date;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIfadmin(boolean ifadmin) {
        this.ifadmin = ifadmin;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                ", ifadmin=" + ifadmin +
                ", create_date=" + create_date +
                ", update_date=" + update_date +
                '}';
    }
}
