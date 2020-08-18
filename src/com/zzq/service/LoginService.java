package com.zzq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzq.entity.userinfo;
import com.zzq.mapper.userinfoMapper;

@Service
public class LoginService {
	@Autowired
	userinfoMapper userinfoDao;
	
	public userinfo selectUserinfoByUsername(String username) {
		return userinfoDao.selectByUsername(username);
	}
}
