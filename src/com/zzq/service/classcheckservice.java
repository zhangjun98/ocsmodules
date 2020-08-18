package com.zzq.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zzq.common.BaseResult;
import com.zzq.common.PageInfo;
import com.zzq.common.classQuery;
import com.zzq.entity.classcheckinfo;
import com.zzq.entity.classinfo;
import com.zzq.entity.userinfo;
import com.zzq.entity.userschoolbusinfo;
import com.zzq.mapper.classcheckinfoMapper;
import com.zzq.mapper.classinfoMapper;
import com.zzq.mapper.classuserMapper;
/**
 * 课程打卡的 service
 * @author EDZ
 *
 */
@Service
public class classcheckservice {
	@Autowired
	classinfoMapper classinfoDao;
	@Autowired
	classuserMapper  ClassuserDao;
	@Autowired
	classcheckinfoMapper classcheckDao;
 /**
    * 用户打卡逻辑
 * @param userid
 * @param classid
 * @return
 */
	public Object classCheck(Integer userid,Integer classid) {
	//添加 是否有打卡记录  有 就判断是否为今天的打卡记录  没有就不管
	List<classcheckinfo> oldchecklist =classcheckDao.selectByUseridAndCalssId(userid, classid);
	classcheckinfo temp= new classcheckinfo();
	Date nowtimeDate =new Date();	
	if(oldchecklist.size()>0) {
		for(int i=0 ;i<oldchecklist.size();i++) {
			temp=oldchecklist.get(i);
			if(temp.getChecktime().getDate()==nowtimeDate.getDate()) {
				return new BaseResult(0, "您今日已打卡");
			}
		}
		
	}
		
	classinfo classinfoDB=	classinfoDao.selectByPrimaryKey(classid);
	
	
	
	Date classbegin=classinfoDB.getClassbegintime();
	Date classend=classinfoDB.getClassendtime();
	
	int flag =0;
	if((nowtimeDate.getHours()>classbegin.getHours())&&(nowtimeDate.getHours()<classend.getHours())) {
		flag =1;
	}else {
		if(nowtimeDate.getHours()==classbegin.getHours()) {
			if(nowtimeDate.getMinutes()>classbegin.getMinutes()) {
				flag =1;
			}
		}if(nowtimeDate.getHours()==classend.getHours()) {
			if(nowtimeDate.getMinutes()<classbegin.getMinutes()) {
				flag =1;
			}
		}
	}
	if(flag==0) {
		return new BaseResult(0, "当前不在打卡时间");
	}else {
		classcheckinfo classcheckinfo =new classcheckinfo();
		classcheckinfo.setUserid(userid);
		classcheckinfo.setChecktime(nowtimeDate);
		classcheckinfo.setClassid(classid);
				
		classcheckDao.insert(classcheckinfo);
		return new BaseResult(200, "打卡成功");
	}
	
 	}

	public Object userGetAllSelectList(classQuery query,userinfo userinfo) {
		Page<userschoolbusinfo> page =PageHelper.startPage(query.getPage(), query.getLimit());
		classcheckDao.selectUserAllCheckList(query.getClassid(),userinfo.getUserid());
		PageInfo pageinfo =new PageInfo();
		pageinfo.setCount(page.getTotal());
		pageinfo.setData(page.getResult());
		return pageinfo;
	}
	public Object SuperuserGetAllSelectList(classQuery query) {
		Page<userschoolbusinfo> page =PageHelper.startPage(query.getPage(), query.getLimit());
		classcheckDao.selectUserAllCheckList(query.getClassid(),query.getSelectuserid());
		PageInfo pageinfo =new PageInfo();
		pageinfo.setCount(page.getTotal());
		pageinfo.setData(page.getResult());
		return pageinfo;
	}
	
}
