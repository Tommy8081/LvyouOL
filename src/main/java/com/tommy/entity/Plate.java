package com.tommy.entity;

import javax.persistence.*;

/**
 * Created by tommy on 2020/4/12 17:41
 */

public class Plate {

    private Long pid;
    private String pname;
    private Long fu_id;
    private String fu_name;
    private String introduction;
    private String owner;
    private Integer send_N;
    private Integer tadoy_N;

    public Plate() {
    }

    public Long getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public Long getFu_id() {
        return fu_id;
    }

    public String getFu_name() {
        return fu_name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getOwner() {
        return owner;
    }

    public Integer getSend_N() {
        return send_N;
    }

    public Integer getTadoy_N() {
        return tadoy_N;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setFu_id(Long fu_id) {
        this.fu_id = fu_id;
    }

    public void setFu_name(String fu_name) {
        this.fu_name = fu_name;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setSend_N(Integer send_N) {
        this.send_N = send_N;
    }

    public void setTadoy_N(Integer tadoy_N) {
        this.tadoy_N = tadoy_N;
    }

    @Override
    public String toString() {
        return "Plate{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", fu_id=" + fu_id +
                ", fu_name='" + fu_name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", owner='" + owner + '\'' +
                ", send_N=" + send_N +
                ", tadoy_N=" + tadoy_N +
                '}';
    }
}
