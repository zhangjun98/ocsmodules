package com.zzq.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzq.common.BaseResult;
import com.zzq.common.investQuery;
import com.zzq.entity.investnote;
import com.zzq.entity.userinfo;
import com.zzq.service.investService;
import com.zzq.util.investUtil;

@Controller
@RequestMapping("invest")
public class investController {
	@Autowired
	investService investService;
	@Autowired
	investUtil investUtil;
	
	@RequestMapping("findlistnote")
	@ResponseBody
	public Object findlistnote(investQuery query,HttpSession session) {
	
		userinfo userinfo=(userinfo)session.getAttribute("user");
			
		investnote investnote = new investnote();
		investnote.setUserid(userinfo.getUserid());
		investnote.setInvestcount(query.getInvestcount());
		Object pageInfo =investService.userselectByuserid(investnote, query);
		return new BaseResult(0, "", pageInfo);
	}
	
	@RequestMapping("delinvestlistone")
	@ResponseBody
	public Object delinvestlistone(@RequestParam Integer actionid) {
		if(investService.userdelinvestone(actionid)==1) {
			return new BaseResult(200, "删除成功");
		}else {return new BaseResult(1, "删除失败");}
		
		
	}
	/**
	 * 充值逻辑核心
	 * @param userid
	 * @param username
	 * @param investcount
	 * @return
	 */
	@RequestMapping(value="investcore",method = RequestMethod.POST)
	@ResponseBody
	public Object invest( @RequestParam("investuserid") Integer userid,
						@RequestParam("investuser") String username,
						@RequestParam("investcount") Double investcount
			) {
		
		return investUtil.investcountCore(userid ,username ,investcount);
	}
	
}
