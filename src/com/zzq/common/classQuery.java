package com.zzq.common;

public class classQuery extends BaseQuery {
	
	public String classname;
	public Integer classid;
	public Integer selectuserid;

	

	public Integer getSelectuserid() {
		return selectuserid;
	}

	public void setSelectuserid(Integer selectuserid) {
		this.selectuserid = selectuserid;
	}

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
		this.classname = classname;
	}
	

}
