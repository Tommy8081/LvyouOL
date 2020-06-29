package com.tommy.entity;


/**
 * Created by tommy on 2020/4/12 17:27
 */
public class Type {


    private Integer tid;
    private String tname;

    public Integer getTid() {
        return tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Override
    public String toString() {
        return "Type{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                '}';
    }
}
