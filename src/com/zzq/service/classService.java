package com.zzq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zzq.common.BaseResult;
import com.zzq.common.PageInfo;
import com.zzq.common.classQuery;
import com.zzq.entity.classuser;
import com.zzq.entity.userinfo;
import com.zzq.mapper.classinfoMapper;
import com.zzq.mapper.classuserMapper;


@Service
public class classService {
	@Autowired
	classinfoMapper classinfoDao;
	@Autowired
	classuserMapper  ClassuserDao;
	/**
	 * 用户获取到所有的课程列表
	 * @param query
	 * @param userinfo
	 * @return
	 */
	public Object userGetAllclassList(classQuery query ,userinfo userinfo) {
		
		Page<classuser> page = PageHelper.startPage(query.getPage(),query.getLimit());
		classinfoDao.UserSelectAllClassList(query.getClassname());
		List<classuser> classinfoList = page.getResult();
		List<classuser> classuserinfoList = new ArrayList<classuser>();
		classuser classusertempfordb = new classuser();
		classuser classusertempforlist = new classuser();
		for(int i=0;i<classinfoList.size();i++) {
			classusertempforlist=classinfoList.get(i);
			classusertempfordb=ClassuserDao.selectByClassidAndUserid(classusertempforlist.getClassid(),userinfo.getUserid());
			if(classusertempfordb==null) {
				classuserinfoList.add(classusertempforlist);
			}
			else {
				classusertempforlist.setActionid(classusertempfordb.getActionid());
				classuserinfoList.add(classusertempforlist);
			}
		}
		
		PageInfo pageInfo =new PageInfo();
		pageInfo.setCount(page.getTotal());
		pageInfo.setData(classuserinfoList);
		return pageInfo;
	}
	/**
	 * 用户进行选课 
	 * @param classuser
	 * @return
	 */
	public Object userSelectClassList(classuser classuser) {
		//选判断该用户是否已经选择了该课程
		if(classuser.getActionid()!=null) {
			return new BaseResult(0,"您已经选择了这门课，请勿重复选择");
		}
		try {
			//再进行选课的操作  写入一个选课的记录
			ClassuserDao.insert(classuser);
		} catch (Exception e) {
			return new BaseResult(0,"选课状态异常");
		}
		return new BaseResult(200,"选课成功，请在你的课程列表查看");

		}
/**
 * 用户获取到所有的已经选课的记录
 * @param query
 * @param userinfo
 * @return
 */
	public Object usergetallselectclass(classQuery query ,userinfo userinfo) {
		Page<classuser> page = PageHelper.startPage(query.getPage(),query.getLimit());
		classinfoDao.UserSelectAllClassList(query.getClassname());
		List<classuser> classinfoList = page.getResult();
		List<classuser> classuserinfoList = new ArrayList<classuser>();
		classuser classusertempfordb = new classuser();
		classuser classusertempforlist = new classuser();
		for(int i=0;i<classinfoList.size();i++) {
			classusertempforlist=classinfoList.get(i);
			classusertempfordb=ClassuserDao.selectByClassidAndUserid(classusertempforlist.getClassid(),userinfo.getUserid());
			if(classusertempfordb!=null){
				classusertempforlist.setActionid(classusertempfordb.getActionid());
				classuserinfoList.add(classusertempforlist);
			}
		}
		
		PageInfo pageInfo =new PageInfo();
		pageInfo.setCount(page.getTotal());
		pageInfo.setData(classuserinfoList);
		return pageInfo;
	}
}
