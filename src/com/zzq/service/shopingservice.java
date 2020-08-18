package com.zzq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzq.common.BaseQuery;
import com.zzq.common.PageInfo;
import com.zzq.entity.bussinfo;
import com.zzq.mapper.bussinfoMapper;

@Service
public class shopingservice {

	@Autowired
	bussinfoMapper bussinfoDao;
	
	public Object getShopingList(bussinfo bussinfo,BaseQuery query) {
		Page<Object> page = PageHelper.startPage(query.getPage(),query.getLimit());
		bussinfoDao.userselectBybussinfo(bussinfo);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setData(page.getResult());
		pageInfo.setCount(page.getTotal());
		return pageInfo;
	}
	
	public int userdelshopingone(Integer actionid) {
		bussinfo bussinfo=new bussinfo();
		bussinfo.setActionid(actionid);
		bussinfo.setIsdel(1);
		return  bussinfoDao.updateByUserdel(bussinfo);
	}
	
	public Object SgetShopingList(BaseQuery query,bussinfo bussinfo) {
		Page<Object> page = PageHelper.startPage(query.getPage(),query.getLimit());
		bussinfoDao.SuperuserselectALL(bussinfo);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setData(page.getResult());
		pageInfo.setCount(page.getTotal());
		return pageInfo;
	}
}
