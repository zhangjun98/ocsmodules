package com.zzq.common;

public class BaseQuery {
	/**
	 *	页码    默认 1
	 */
	private Integer page = 1;
	/**
	 * 	每页的数据长度  默认  10
	 */
	private Integer limit = 10;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	
	
}

