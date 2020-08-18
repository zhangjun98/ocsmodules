package com.zzq.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import com.zzq.service.shopingservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zzq.common.BaseQuery;
import com.zzq.common.BaseResult;
import com.zzq.common.PageInfo;
import com.zzq.entity.bussinfo;
import com.zzq.entity.userinfo;

@Controller
@RequestMapping("shoping")
public class shopingController {
	@Autowired
	shopingservice shopingservice;
	
	@RequestMapping("findshopinglist")
	@ResponseBody
	public Object findshopinglist(
			BaseQuery query,
			@RequestParam(value="localid",required = false) Integer localid,
			HttpSession session) {
		userinfo userinfo=(userinfo)session.getAttribute("user");
		bussinfo bussinfo =new bussinfo();
		bussinfo.setLocalid(localid);
		bussinfo.setUserid(userinfo.getUserid());
		Object pageInfo =shopingservice.getShopingList(bussinfo,query);
		return new BaseResult(0, "", pageInfo);
		
	}
	@RequestMapping("delshopinglistone")
	@ResponseBody
	public Object delshopinglistone(@RequestParam Integer actionid) {
		if(shopingservice.userdelshopingone(actionid)==1) {
			return new BaseResult(200, "删除成功");
		}else {return new BaseResult(1, "删除失败");}
		
		
	}
	
	@RequestMapping("Superfindshopinglist")
	@ResponseBody
	public Object Superfindshopinglist(
			BaseQuery query,
			@RequestParam(value="localid",required = false) Integer localid
			,@RequestParam(value="userid",required = false) Integer userid
			) {
		bussinfo bussinfo =new bussinfo();
		bussinfo.setLocalid(localid);
		bussinfo.setUserid(userid);
		Object pageInfo =shopingservice.SgetShopingList(query,bussinfo);
		return new BaseResult(0, "", pageInfo);
		
	}
	
}
