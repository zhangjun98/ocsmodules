package com.zzq.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzq.common.BaseResult;
import com.zzq.common.PageInfo;
import com.zzq.common.busQuery;
import com.zzq.entity.booklendinfo;
import com.zzq.entity.bussinfo;
import com.zzq.entity.schoolbusorder;
import com.zzq.entity.userinfo;
import com.zzq.service.BusService;
import com.zzq.service.investService;

@Controller
@RequestMapping("bus")
public class schoolbusController {
	@Autowired
	BusService buservice;
	
	/**
	 * 用户搜索返回的 所有的  校车数据
	 * @return 
	 */
	@RequestMapping("usergetallschoolbus")
	@ResponseBody
	public Object  usergetallschoolbus(busQuery query,HttpSession session) {
		userinfo userinfo=(userinfo)session.getAttribute("user");
		PageInfo pageInfo= (PageInfo) buservice.usergetAllbusList(query, userinfo);
		return new BaseResult(0, "", pageInfo);
		
	}
	
	/**
	 * 用户申报校车
	 *  '
	 * @return 
	 */
	@RequestMapping("subline")
	@ResponseBody
	public Object  usersubschoolbus(@RequestParam("lineid") Integer lineid,
			@RequestParam("busstart") String busstart,
			@RequestParam("busend") String busend,
			@RequestParam("price") Double price
			,HttpSession session) {
		userinfo userinfo=(userinfo)session.getAttribute("user");
		//service 进行查询  lineid price 
		 schoolbusorder schoolbusorder = new schoolbusorder();
		 schoolbusorder.setLineid(lineid);
		 schoolbusorder.setUserid(userinfo.getUserid()); 
		 bussinfo bussinfo = new bussinfo(); 
		 bussinfo.setActionmoney(price);
		
		  return buservice.usersubline(schoolbusorder, bussinfo);
		
		
	}
	
	// subline   userid   lineid
	
	
	
	// 
	/**
	 * 用户搜索返回的 所有的  校车数据
	 * @return 
	 */
	@RequestMapping("userbussline")
	@ResponseBody
	public Object  userbussline(busQuery query,HttpSession session) {
		userinfo userinfo=(userinfo)session.getAttribute("user");
		System.out.println("=====================");
		System.out.println(query.getBussstart());
		System.out.println("=====================");
		PageInfo pageInfo=  (PageInfo) buservice.getUserschoolbusorder(query, userinfo);
		return new BaseResult(0, "", pageInfo);
		
	}
	
	@RequestMapping("Superuserbussline")
	@ResponseBody
	public Object  Superuserbussline(busQuery query) {
		PageInfo pageInfo=  (PageInfo) buservice.SupergetUserschoolbusorder(query);
		return new BaseResult(0, "", pageInfo);
		
	}
	
}
