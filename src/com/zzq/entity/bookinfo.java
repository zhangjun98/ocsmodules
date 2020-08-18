package com.zzq.entity;

import java.util.Date;

public class bookinfo {
	
    private Integer bookid;

    private String bookname;

    private Integer isgrounding;

    private Double price;

    private Date subdate;

    private Integer classify;
    
    
    
    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname == null ? null : bookname.trim();
    }

    public Integer getIsgrounding() {
        return isgrounding;
    }

    public void setIsgrounding(Integer isgrounding) {
        this.isgrounding = isgrounding;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getSubdate() {
        return subdate;
    }

    public void setSubdate(Date subdate) {
        this.subdate = subdate;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

	@Override
	public String toString() {
		return "bookinfo [bookid=" + bookid + ", bookname=" + bookname + ", isgrounding=" + isgrounding + ", price="
				+ price + ", subdate=" + subdate + ", classify=" + classify + "]";
	}
    
}