package com.zzq.entity;

import java.util.Date;

public class schoolbusorder extends shoolbusinfo {
    private Integer actionid;

   

    private Integer userid;

    private Date ordertime;

    private Integer issuccess;

    private Integer isdel;

    public Integer getActionid() {
        return actionid;
    }

    public void setActionid(Integer actionid) {
        this.actionid = actionid;
    }


    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Integer getIssuccess() {
        return issuccess;
    }

    public void setIssuccess(Integer issuccess) {
        this.issuccess = issuccess;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
}