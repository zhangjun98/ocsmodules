package com.zzq.common;

public class BookLendQuery extends BaseQuery{

	public String bookname;
	public Integer bookclass;
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public Integer getBookclass() {
		return bookclass;
	}
	public void setBookclass(Integer bookclass) {
		this.bookclass = bookclass;
	}
	
}
