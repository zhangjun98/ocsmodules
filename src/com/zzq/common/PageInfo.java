package com.zzq.common;
/**
 * @ClassName: PageInfo 
 * @Description: 自定义的page对象  为了适应 layui
 * @date: 2019年11月29日 上午10:10:01
 */
public class PageInfo {
	
	/**
	 * 	数据
	 */
	private Object data;
	
	/**
	 * 	总数据条数
	 */
	private Long count;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	
	
	

}
