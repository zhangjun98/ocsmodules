package com.zzq.entity;

import java.util.Date;

public class booklendinfo  extends bookinfo{
	
    private Integer actionid;

    private Integer userid;

    private Date lendtime;

    private Integer isover;

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

  

    public Date getLendtime() {
        return lendtime;
    }

    public void setLendtime(Date lendtime) {
        this.lendtime = lendtime;
    }

    public Integer getIsover() {
        return isover;
    }

    public void setIsover(Integer isover) {
        this.isover = isover;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

	@Override
	public String toString() {
		return "booklendinfo [actionid=" + actionid + ", userid=" + userid + ", lendtime=" + lendtime + ", isover="
				+ isover + ", isdel=" + isdel + "]";
	}
    
}