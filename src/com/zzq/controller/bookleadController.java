package com.zzq.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zzq.common.BaseResult;
import com.zzq.common.BookLendQuery;
import com.zzq.common.BookQuery;
import com.zzq.common.PageInfo;
import com.zzq.entity.booklendinfo;
import com.zzq.entity.bussinfo;
import com.zzq.entity.userinfo;
import com.zzq.service.bookservice;
import com.zzq.service.investService;

@Controller
@RequestMapping("book")
public class bookleadController {

	@Autowired
	bookservice bookservice;
@Autowired
	investService investService;
	@RequestMapping(value = "getallbooklist", method = RequestMethod.GET)
	@ResponseBody
	public Object getallbooklist(BookQuery query,HttpSession session) {
		userinfo userinfo=(userinfo)session.getAttribute("user");
		PageInfo pageInfo = (PageInfo) bookservice.usergetAllbookList(query,userinfo);
		return new BaseResult(0, "", pageInfo);

	}

	@RequestMapping(value = "userleadbook", method = RequestMethod.POST)
	@ResponseBody
	public Object userleadbook(@RequestParam(value = "bookid", required = false) Integer bookid,
			@RequestParam(value = "leadbookprice", required = false) Double leadbookprice,
			@RequestParam(value = "leadbookname", required = false) String leadbookname,
			@RequestParam(value = "leadbookuser", required = false) String leadbookuser,
			HttpSession session
			) {
		userinfo userinfo=(userinfo)session.getAttribute("user");
		if(userinfo==null) {return new BaseResult(0, "登录状态已过期，请重新登录");}
		 booklendinfo booklendinfo = new booklendinfo();
		 booklendinfo.setBookid(bookid);
		 booklendinfo.setUserid(userinfo.getUserid()); 
		 bussinfo bussinfo = new bussinfo(); 
		 bussinfo.setActionmoney(leadbookprice);
		 Double countInteger= investService.selectusercount(userinfo.getUserid());
			session.setAttribute("count", countInteger);
		  return bookservice.userleadbook(booklendinfo,bussinfo);
		 

	}

	@RequestMapping(value = "getuserbookleadlist")
	@ResponseBody
	public Object getuserbooklist(BookLendQuery query, HttpSession session) {
		userinfo userinfo=(userinfo)session.getAttribute("user");
		if(userinfo==null) {return new BaseResult(0, "登录状态已过期，请重新登录");}
		PageInfo pageInfo=  (PageInfo) bookservice.getUserLeadBookByUser(query, userinfo.getUserid());
		return new BaseResult(0, "", pageInfo);

	}
	/**
	 * 用户申请归还图书
	 * @param actionid
	 * @return
	 */
	@RequestMapping(value = "returnbook")
	@ResponseBody
	public Object returnbook(@RequestParam("actionid") Integer actionid) {
		try {
			booklendinfo booklendinfo =new booklendinfo();
			booklendinfo.setActionid(actionid);
			booklendinfo.setIsover(3);
		return	bookservice.userReturnBook(booklendinfo);
			
		} catch (Exception e) {
			return new BaseResult(0, "归还状态异常");
		}
	}
	/**
	 * 超级用户获取到所有的 用户借阅信息    
	 * @param query
	 * @param session
	 * @return
	 */

	@RequestMapping(value = "Sgetallleadbook")
	@ResponseBody
	public Object Sgetallleadbook(BookLendQuery query, HttpSession session) {
		userinfo userinfo=(userinfo)session.getAttribute("user");
		if(userinfo==null) {return new BaseResult(0, "登录状态已过期，请重新登录");}
		PageInfo pageInfo=  (PageInfo) bookservice.getUserLeadBookByUser(query, null);
		return new BaseResult(0, "", pageInfo);

	}
	/**
	 * 超级用户同意用户归还图书信息
	 * @param actionid
	 * @return
	 */
	@RequestMapping(value = "Sagreebook")
	@ResponseBody
	public Object Sagreebook(@RequestParam("actionid") Integer actionid) {
		try {
			booklendinfo booklendinfo =new booklendinfo();
			booklendinfo.setActionid(actionid);
			booklendinfo.setIsover(0);
			bookservice.userReturnBook(booklendinfo);
			 return new BaseResult(200, "已确认");
		} catch (Exception e) {
			return new BaseResult(0, "归还状态异常");
		}
	}
	
	/**
	 * 超级用户获取到所有的 用户借阅信息  进行操作  
	 * @param query
	 * @param session
	 * @return
	 */

	@RequestMapping(value = "Sgetallleadbookstatue")
	@ResponseBody
	public Object Sgetallleadbookstatue(BookLendQuery query, HttpSession session) {
		userinfo userinfo=(userinfo)session.getAttribute("user");
		if(userinfo==null) {return new BaseResult(0, "登录状态已过期，请重新登录");}
		PageInfo pageInfo=  (PageInfo) bookservice.getUserLeadBookByUserstatue(query, null);
		return new BaseResult(0, "", pageInfo);

	}
}
