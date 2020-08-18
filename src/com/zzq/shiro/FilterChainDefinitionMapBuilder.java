package com.zzq.shiro;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		//顺序有要求·不能改
		map.put("/view/common/login.jsp", "anon");
		//这里如果写anon就是走的springMVC的控制器拦截，如果写的 authc就是走的formAuthentcationFiter进行登录验证
		map.put("/common/login", "anon");
		map.put("/common/loginout", "logout");
		map.put("/common/toindex", "user");
		map.put("/common/toS**", "authc,roles[super]");
		map.put("/common/to**", "authc,roles[user]");
		map.put("/static/**", "anon");
		map.put("/uploads/**", "anon");
		map.put("/**", "authc");
		
		return map;
	}
	
}
