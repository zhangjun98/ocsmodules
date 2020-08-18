package com.zzq.controller;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzq.common.BaseResult;
import com.zzq.entity.userinfo;
import com.zzq.service.investService;

@Controller
@RequestMapping("common")
public class commonController {
	
	@Autowired
	investService investService;
	
	@RequestMapping(value = "toshoping" ,method=RequestMethod.GET)
		public String toShoping(HttpSession session) {
		userinfo userinfo=(userinfo)session.getAttribute("user");
		if(userinfo==null) {return "common/404";}
			
			return "usercommon/shopinginfo";
		}
		
	/**
	 * 转发的时候将余额信息发送到页面上
	 * @param session
	 * @return
	 */
		@RequestMapping(value = "toinvest" ,method=RequestMethod.GET)
		public String toinvest(HttpSession session ) {
			userinfo userinfo=(userinfo)session.getAttribute("user");
			if(userinfo==null) {return "common/404";}
				
			Double countInteger= investService.selectusercount(userinfo.getUserid());
			session.setAttribute("count", countInteger);
			return "invest/investindex";
		}
		
		/**
		 * 用户转发 到书籍商城
		 * @param session
		 * @return
		 */
			@RequestMapping(value = "tobookstore" ,method=RequestMethod.GET)
			public String tobookstore(HttpSession session ) {
				
				userinfo userinfo=(userinfo)session.getAttribute("user");
				if(userinfo==null) {return "common/404";}
				//用作余额状态更新
				Double countInteger= investService.selectusercount(userinfo.getUserid());
				session.setAttribute("count", countInteger);
				return "usercommon/bookstore";
			}
			/**
			 * 用户转发 到我的书籍借阅
			 * @param session
			 * @return
			 */
				@RequestMapping(value = "tomyleadbook" ,method=RequestMethod.GET)
				public String tomyleadbook(HttpSession session ) {
					
					userinfo userinfo=(userinfo)session.getAttribute("user");
					if(userinfo==null) {return "common/404";}
					//用作余额状态更新
					Double countInteger= investService.selectusercount(userinfo.getUserid());
					session.setAttribute("count", countInteger);
					return "usercommon/myleadbook";
				}
				/**
				 * 用户转发 到校车路线表
				 * @param session
				 * @return
				 */
				@RequestMapping(value = "toschoolbusline" ,method=RequestMethod.GET)
				public String toschoolbusline(HttpSession session ) {
						
				userinfo userinfo=(userinfo)session.getAttribute("user");
					if(userinfo==null) {return "common/404";}
					
					return "usercommon/schoolbusline";
				}
				
				/**
				 * 用户转发 到我的预约路线
				 * @param session
				 * @return
				 */
				@RequestMapping(value = "tomyschoolbus" ,method=RequestMethod.GET)
				public String tomyschoolbus(HttpSession session ) {
						
				userinfo userinfo=(userinfo)session.getAttribute("user");
					if(userinfo==null) {return "common/404";}
					return "usercommon/myschoolbus";
				}

				/**
				 * 用户转发 到所有课程
				 * @param session
				 * @return
				 */
				@RequestMapping(value = "toclasslist" ,method=RequestMethod.GET)
				public String toclasslist(HttpSession session ) {
						
				userinfo userinfo=(userinfo)session.getAttribute("user");
					if(userinfo==null) {return "common/404";}
					return "usercommon/classlist";
				}

				/**
				 * 用户转发 到我的上课打卡
				 * @param session
				 * @return
				 */
				@RequestMapping(value = "tomyclasscheck" ,method=RequestMethod.GET)
				public String tomyclasscheck(HttpSession session ) {
						
				userinfo userinfo=(userinfo)session.getAttribute("user");
					if(userinfo==null) {return "common/404";}
					return "usercommon/classcheck";
				}
				/**
				 * 用户转发 到我的课程
				 * @param session
				 * @return
				 */
				@RequestMapping(value = "tomyclass" ,method=RequestMethod.GET)
				public String tomyclass(HttpSession session ) {
						
				userinfo userinfo=(userinfo)session.getAttribute("user");
					if(userinfo==null) {return "common/404";}
					return "usercommon/myclasslist";
				}


				/**
				 * 用户转发 到课程列表
				 * @param session
				 * @return
				 */
				@RequestMapping(value = "toSclass" ,method=RequestMethod.GET)
				public String toSclass(HttpSession session ) {
						
				userinfo userinfo=(userinfo)session.getAttribute("user");
					if(userinfo==null) {return "common/404";}
					return "supercommon/Sclass";
				}
				/**
				 * 用户转发 到图书归还管理
				 * @param session
				 * @return
				 */
				@RequestMapping(value = "toSbook" ,method=RequestMethod.GET)
				public String toSbook(HttpSession session ) {
						
				userinfo userinfo=(userinfo)session.getAttribute("user");
					if(userinfo==null) {return "common/404";}
					return "supercommon/Sbook";
				}


				/**
				 * 用户转发 到归还图书管理
				 * @param session
				 * @return
				 */
				@RequestMapping(value = "toSbookall" ,method=RequestMethod.GET)
				public String toSbookall(HttpSession session ) {
						
				userinfo userinfo=(userinfo)session.getAttribute("user");
					if(userinfo==null) {return "common/404";}
					return "supercommon/Sbookall";
				}
				
				@RequestMapping(value = "toSclasscheck" ,method=RequestMethod.GET)
				public String toSclasscheck(HttpSession session ) {
						
				userinfo userinfo=(userinfo)session.getAttribute("user");
					if(userinfo==null) {return "common/404";}
					return "supercommon/Sclass";
				}
				
				@RequestMapping(value = "toSschoolbus" ,method=RequestMethod.GET)
				public String toSschoolbus(HttpSession session ) {
						
				userinfo userinfo=(userinfo)session.getAttribute("user");
					if(userinfo==null) {return "common/404";}
					return "supercommon/Sschoolbus";
				}
				
				@RequestMapping(value = "toSshoping" ,method=RequestMethod.GET)
				public String toSshoping(HttpSession session ) {
						
				userinfo userinfo=(userinfo)session.getAttribute("user");
					if(userinfo==null) {return "common/404";}
					return "supercommon/Sshoping";
				}
}
