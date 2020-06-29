package com.tommy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;


import javax.persistence.*;
import java.util.Date;



/**
 * Created by tommy on 2020/4/12 16:33
 */

public class Post {

    private Integer id;
    private String title;
    private String index_img;
    private String content;
    private Boolean tag;
    private Integer seeN;
    private Integer zanN;
    private Integer cangN;
    private boolean recommend;
    private boolean published;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private Object type;
    private Object user;

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIndexImg() {
        return index_img;
    }

    public String getContent() {
        return content;
    }

    public Integer getSeeN() {
        return seeN;
    }

    public Integer getZanN() {
        return zanN;
    }

    public Integer getCangN() {
        return cangN;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public boolean isPublished() {
        return published;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateDate() {
        return createDate;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIndexImg(String indexImg) {
        this.index_img = indexImg;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSeeN(Integer seeN) {
        this.seeN = seeN;
    }

    public void setZanN(Integer zanN) {
        this.zanN = zanN;
    }

    public void setCangN(Integer cangN) {
        this.cangN = cangN;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getTag() {
        return tag;
    }

    public void setTag(Boolean tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", indexImg='" + index_img + '\'' +
                ", content='" + content + '\'' +
                ", tag=" + tag +
                ", seeN=" + seeN +
                ", zanN=" + zanN +
                ", cangN=" + cangN +
                ", recommend=" + recommend +
                ", published=" + published +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
