package com.zzq.entity;

import java.util.Date;

public class bussinfo {
    private Integer actionid;

    private Integer localid;

    private Integer userid;

    private Date actiontime;

    private Double actionmoney;
    
    private Double blance;
    
    private Integer isdel;

    public Double getBlance() {
		return blance;
	}

	public void setBlance(Double blance) {
		this.blance = blance;
	}

	public Integer getActionid() {
        return actionid;
    }

    public void setActionid(Integer actionid) {
        this.actionid = actionid;
    }

    public Integer getLocalid() {
        return localid;
    }

    public void setLocalid(Integer localid) {
        this.localid = localid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getActiontime() {
        return actiontime;
    }

    public void setActiontime(Date actiontime) {
        this.actiontime = actiontime;
    }

   

    public Double getActionmoney() {
		return actionmoney;
	}

	public void setActionmoney(Double actionmoney) {
		this.actionmoney = actionmoney;
	}

	public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
}