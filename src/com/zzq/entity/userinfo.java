package com.zzq.entity;

import java.io.Serializable;

public class userinfo implements Serializable{
    private Integer userid;

    private String username;

    private String password;

    private Integer usertype;

    private Integer usergrade;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Integer getUsergrade() {
        return usergrade;
    }

    public void setUsergrade(Integer usergrade) {
        this.usergrade = usergrade;
    }

	@Override
	public String toString() {
		return "userinfo [userid=" + userid + ", username=" + username + ", password=" + password + ", usertype="
				+ usertype + ", usergrade=" + usergrade + "]";
	}
    
}