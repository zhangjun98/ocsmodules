package com.zzq.entity;

import java.util.Date;

public class classinfo {
    private Integer classid;

    private String classname;

    private Date classbegintime;
    
    private Date classendtime;
    
    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public Date getClassbegintime() {
        return classbegintime;
    }

    public void setClassbegintime(Date classbegintime) {
        this.classbegintime = classbegintime;
    }

    public Date getClassendtime() {
        return classendtime;
    }

    public void setClassendtime(Date classendtime) {
        this.classendtime = classendtime;
    }
	@Override
	public String toString() {
		return "classinfo [classid=" + classid + ", classname=" + classname + ", classbegintime=" + classbegintime
				+ "]";
	}
    
}