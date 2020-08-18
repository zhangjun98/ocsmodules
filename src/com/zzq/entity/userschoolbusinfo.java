package com.zzq.entity;

/**
 * 对应用户是否预定   对于mapper  使用这个类进行接收  然后穿提到前台
 * @author EDZ
 *
 */
public class userschoolbusinfo extends shoolbusinfo {
	
	//对应用户是否预定   对于mapper  使用这个类进行接收  然后穿提到前台
	private Integer  issub;

	public Integer getIssub() {
		return issub;
	}

	public void setIssub(Integer issub) {
		this.issub = issub;
	}
	
	
	

}
