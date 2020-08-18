package com.zzq.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzq.common.BaseResult;
import com.zzq.entity.userinfo;
import com.zzq.service.LoginService;
import com.zzq.service.investService;

@Controller
@RequestMapping(value = "/common")
public class LoginController {
	@Autowired
	LoginService loginservice;
	@Autowired
	investService investService;
	/*
	 * @RequestMapping(value="/loginout",method = RequestMethod.GET) public String
	 * loginout(HttpSession session) { session.invalidate(); return "/common/login";
	 * }
	 */
	
	
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String loginUI(HttpServletRequest request) {
		return "/common/login";
	}

	@RequestMapping(value="/login",method = RequestMethod.POST)
	@ResponseBody
	public Object login(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpSession session) {
		Subject currentUser = SecurityUtils.getSubject();
		System.out.println("+++++++++controller中的subject++++++++++");
		System.out.println(currentUser.hashCode());
		if (!currentUser.isAuthenticated()) {
        	// 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            // rememberme
            token.setRememberMe(true);
            try {
            	System.out.println("========================执行登录方法" );
            	// 执行登录. 
                currentUser.login(token);
				
    			return new BaseResult(200, "登陆成功");
            } 
           
            catch (AuthenticationException ae) {
            	System.out.println("登录失败: " + ae.getMessage());
            	return new BaseResult(1, "用户名或者密码错误");
            }
        }else {
			 return "common/index";
		}
	}
	
	@RequestMapping(value="toindex",method = RequestMethod.GET)
	public String toindex(HttpServletRequest request) {
		return "common/index";
	}
}
