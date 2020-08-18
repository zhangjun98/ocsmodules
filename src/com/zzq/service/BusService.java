package com.zzq.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzq.common.BaseResult;
import com.zzq.common.BookQuery;
import com.zzq.common.PageInfo;
import com.zzq.common.busQuery;
import com.zzq.entity.booklendinfo;
import com.zzq.entity.bussinfo;
import com.zzq.entity.schoolbusorder;
import com.zzq.entity.userbookinfo;
import com.zzq.entity.userinfo;
import com.zzq.entity.userschoolbusinfo;
import com.zzq.mapper.schoolbusorderMapper;
import com.zzq.mapper.shoolbusinfoMapper;
import com.zzq.util.investUtil;

@Service
public class BusService {

	@Autowired
	schoolbusorderMapper  mySchoolBusDao;
	@Autowired
	shoolbusinfoMapper schoolbuslineDao;
	@Autowired
	investUtil investUtil;
	

	/**
	 * 用户获取到的所有 已经 发布的路线
	 * @param query
	 * @return
	 */
	public Object usergetAllbusList (busQuery query,userinfo userinfo) {
		
		Page<userschoolbusinfo> page =PageHelper.startPage(query.getPage(), query.getLimit());
		schoolbuslineDao.userselectAllbusList(query.getBussstart(),query.getBussend());
		
		List<userschoolbusinfo> businfoList =(List<userschoolbusinfo>)page.getResult();
		
		List<userschoolbusinfo > userbusinfoList = new ArrayList<userschoolbusinfo>() ;
		int lineid;
		int userid=userinfo.getUserid();
		schoolbusorder orderinfo = new schoolbusorder();
		orderinfo.setUserid(userid);
		userschoolbusinfo userschoolbusinfo; 
		for(int i=0;i<businfoList.size();i++) {
			lineid=businfoList.get(i).getLineid();
			orderinfo.setLineid(lineid);
			schoolbusorder booklendinfo= mySchoolBusDao.selectByUseridAndLineid(orderinfo);
			if(booklendinfo==null)
			{
				userbusinfoList.add(businfoList.get(i));
				
			}else {
				userschoolbusinfo=businfoList.get(i);
				if(booklendinfo.getIssuccess()==1) {
					userschoolbusinfo.setIssub(1);//已预订
				}
				userbusinfoList.add(userschoolbusinfo);
				
			}
		}
		
		PageInfo pageinfo =new PageInfo();
		pageinfo.setCount(page.getTotal());
		pageinfo.setData(userbusinfoList);
		return pageinfo;
	}
	

	/**
	 * 用户预定路线
	 * @param query
	 * @return
	 */
	public Object usersubline (schoolbusorder schoolbusorder,bussinfo bussindfo) {
	
		try {
			//
			schoolbusorder schoolbusorderDB =mySchoolBusDao.selectByUseridAndLineid(schoolbusorder);
				if(schoolbusorderDB!=null) {
					if(schoolbusorderDB.getIssuccess()==1) {
						return new BaseResult(0,"您以预定该线路");
					}
				}
			int	blancestatue	=investUtil.subline(schoolbusorder, bussindfo);
			if(blancestatue==0) {return new BaseResult(0,"您的余额不足，请先充值");}
		} catch (Exception e) {
			return new BaseResult(0,"路线预定失败");
		}
		return new BaseResult(200,"路线预定成功");
	
	
	}
	
	
	public Object getUserschoolbusorder(busQuery query,userinfo userinfo) {
		
		Page<Object> page=PageHelper.startPage(query.getPage(),query.getLimit());
		mySchoolBusDao.selectByuserid(query.getBussstart(),query.getBussend(),userinfo.getUserid());
		PageInfo pageinfo =new PageInfo();
		pageinfo.setCount(page.getTotal());
		pageinfo.setData(page.getResult());
		return pageinfo;
		
	}
	

	public Object SupergetUserschoolbusorder(busQuery query) {
		
		Page<Object> page=PageHelper.startPage(query.getPage(),query.getLimit());
		mySchoolBusDao.selectALL(query.getBussstart(),query.getBussend());
		PageInfo pageinfo =new PageInfo();
		pageinfo.setCount(page.getTotal());
		pageinfo.setData(page.getResult());
		return pageinfo;
		
	}
	
}
