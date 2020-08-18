package com.zzq.entity;

public class classuser extends classinfo{
	
    private Integer actionid;

    private Integer userid;
   

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

	@Override
	public String toString() {
		return "classuser [actionid=" + actionid + ", userid=" + userid + "]";
	}

}