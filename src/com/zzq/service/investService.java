package com.zzq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzq.common.BaseQuery;
import com.zzq.common.PageInfo;
import com.zzq.common.investQuery;
import com.zzq.entity.bussinfo;
import com.zzq.entity.investnote;
import com.zzq.entity.userinfo;
import com.zzq.mapper.investMapper;
import com.zzq.mapper.investnoteMapper;

@Service
public class investService {
	@Autowired
	investMapper investDAO;
	@Autowired
	investnoteMapper investnoteDAO;
	
	//查询余额
   public Double selectusercount(Integer userid) {
	   
	return investDAO.selectByUserId(userid).getBalance();
    }
   
   
   public Object userselectByuserid(investnote investnote,investQuery query) {
		Page<Object> page = PageHelper.startPage(query.getPage(),query.getLimit());
		investnoteDAO.userselectByuseridAndCount(investnote);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setData(page.getResult());
		pageInfo.setCount(page.getTotal());
		return pageInfo;
	}
   
   public int userdelinvestone(Integer actionid) {
	   
	   investnote investnote =new investnote();
	   investnote.setActionid(actionid);
	   	investnote.setIsdel(1);
	   return investnoteDAO.updateByPrimaryKeySelective(investnote);
   }
}
