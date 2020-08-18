package com.zzq.entity;

import java.util.Date;

public class investnote {
    private Integer actionid;

    private Integer userid;

    private Double investcount;
    
	private Double count;
    
	private Date investtime;

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

	public Double getInvestcount() {
		return investcount;
	}

	public void setInvestcount(Double investcount) {
		this.investcount = investcount;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public Date getInvesttime() {
		return investtime;
	}

	public void setInvesttime(Date investtime) {
		this.investtime = investtime;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	@Override
	public String toString() {
		return "investnote [actionid=" + actionid + ", userid=" + userid + ", investcount=" + investcount + ", count="
				+ count + ", investtime=" + investtime + ", isdel=" + isdel + "]";
	}

	
}