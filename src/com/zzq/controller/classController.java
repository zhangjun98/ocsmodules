package com.zzq.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zzq.common.BaseResult;
import com.zzq.common.PageInfo;
import com.zzq.common.classQuery;
import com.zzq.entity.booklendinfo;
import com.zzq.entity.bussinfo;
import com.zzq.entity.classuser;
import com.zzq.entity.userinfo;
/**
 * 该类主要是用于处理课程管理的一部分操作
 * @author EDZ
 *
 */
import com.zzq.service.classService;
import com.zzq.service.classcheckservice;
@RequestMapping("class")
@Controller
public class classController {
	
	@Autowired
	classService classService;
	/**
	 * 用户获取到所有的 课程信息
	 * @param query
	 * @param session
	 * @return
	 */
	@RequestMapping("userGetAllClassList")
	@ResponseBody
	public Object userGetAllClassList(classQuery query,HttpSession session) {
		userinfo userinfo=(userinfo)session.getAttribute("user");
	PageInfo pageInfo = (PageInfo) classService.userGetAllclassList(query,userinfo);
	return new BaseResult(0, "", pageInfo);

		
	}
/**
 * 用户选择课程
 * @param classid
 * @param session
 * @return
 */
	@RequestMapping(value = "userselectclass", method = RequestMethod.POST)
	@ResponseBody
	public Object userselectclass(@RequestParam(value = "classid", required = false) Integer classid,
			@RequestParam(value = "actionid", required = false) Integer actionid,
			HttpSession session
			) {
		userinfo userinfo=(userinfo)session.getAttribute("user");
		if(userinfo==null) {return new BaseResult(0, "登录状态已过期，请重新登录");}
		 classuser classuser=new classuser();
		 classuser.setClassid(classid);
		 classuser.setActionid(actionid);
		 classuser.setUserid(userinfo.getUserid()); 
		  return classService.userSelectClassList(classuser);
}
	/**
	 * 用户获取到所有的 课程信息
	 * @param query
	 * @param session
	 * @return
	 */
	@RequestMapping("userclassinfo")
	@ResponseBody
	public Object userclassinfo(classQuery query,HttpSession session) {
		userinfo userinfo=(userinfo)session.getAttribute("user");
	PageInfo pageInfo = (PageInfo) classService.usergetallselectclass(query,userinfo);
	return new BaseResult(0, "", pageInfo);
	}

	@Autowired
	classcheckservice  classcheckservice;
	/**
	 * 用户打卡
	 * @return
	 */
	@RequestMapping("classcheck")
	@ResponseBody
	public Object classcheck(@RequestParam("actionid") Integer actionid,
			@RequestParam("classid") Integer classid,
			HttpSession session
			) {
		userinfo userinfo=(userinfo)session.getAttribute("user");
		
		try {
			return classcheckservice.classCheck(userinfo.getUserid(), classid);
		} catch (Exception e) {
			return new BaseResult(0, "打卡状态异常");
		}
	
	}
	@RequestMapping("usergetAllCheckList")
	@ResponseBody
	public Object usergetAllCheckList(classQuery query,HttpSession session) {
		userinfo userinfo=(userinfo)session.getAttribute("user");
		try {
			PageInfo pageInfo = (PageInfo) classcheckservice.userGetAllSelectList(query,userinfo);
			return new BaseResult(0, "", pageInfo);
		} catch (Exception e) {
			return new BaseResult(0, "");
		}
	
	}
	@RequestMapping("SuperusergetAllCheckList")
	@ResponseBody
	public Object SuperusergetAllCheckList(classQuery query,HttpSession session) {
		try {
			PageInfo pageInfo = (PageInfo) classcheckservice.SuperuserGetAllSelectList(query);
			return new BaseResult(0, "", pageInfo);
		} catch (Exception e) {
			return new BaseResult(0, "");
		}
	
	}
	
}
