package com.tommy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

import java.util.Date;


/**
 * Created by tommy on 2020/4/12 17:37
 */
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    private String concent;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    private Integer parent_comment_cid;
    private int zanN;
    private Object post;
    private Object user;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getConcent() {
        return concent;
    }

    public void setConcent(String concent) {
        this.concent = concent;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getParent_comment_cid() {
        return parent_comment_cid;
    }

    public void setParent_comment_cid(Integer parent_comment_cid) {
        this.parent_comment_cid = parent_comment_cid;
    }

    public int getZanN() {
        return zanN;
    }

    public void setZanN(int zanN) {
        this.zanN = zanN;
    }

    public Object getPost() {
        return post;
    }

    public void setPost(Object post) {
        this.post = post;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "cid=" + cid +
                ", concent='" + concent + '\'' +
                ", createDate=" + createDate +
                ", parent_comment_cid=" + parent_comment_cid +
                ", zanN=" + zanN +
                ", post=" + post +
                ", user=" + user +
                '}';
    }
}
