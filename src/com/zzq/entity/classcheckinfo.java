package com.zzq.entity;

import java.util.Date;

public class classcheckinfo extends classinfo {
	
    private Integer actionid;

    private Integer userid;

    private Date checktime;

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

    public Date getChecktime() {
        return checktime;
    }

    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }

	@Override
	public String toString() {
		return "classcheckinfo [actionid=" + actionid + ", userid=" + userid + ", checktime=" + checktime + "]";
	}
    
}