package com.zzq.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.ql.parse.QBSubQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zzq.common.BaseResult;
import com.zzq.common.BookLendQuery;
import com.zzq.common.BookQuery;
import com.zzq.common.PageInfo;
import com.zzq.entity.bookinfo;
import com.zzq.entity.booklendinfo;
import com.zzq.entity.bussinfo;
import com.zzq.entity.userbookinfo;
import com.zzq.entity.userinfo;
import com.zzq.mapper.bookinfoMapper;
import com.zzq.mapper.booklendinfoMapper;
import com.zzq.util.investUtil;

@Service
public class bookservice {
	@Autowired
	bookinfoMapper bookinfoDAO;
	@Autowired
	booklendinfoMapper bookleadinfoDAO;
	
	@Autowired
	investUtil investUtil;
	
	/**
	 * 用户获取到的已经上架的所有图书
	 * @param query
	 * @return
	 */
	public Object usergetAllbookList (BookQuery query,userinfo userinfo) {
		
		Page<userbookinfo> page =PageHelper.startPage(query.getPage(), query.getLimit());
		bookinfoDAO.userselectAllBookList(query.getBookclass(),query.getBookname());
		
		List<userbookinfo> bookinfoList =(List<userbookinfo>)page.getResult();
		
		List<userbookinfo>userbookinfoList = new ArrayList<userbookinfo>();
		int bookid;
		int userid=userinfo.getUserid();
		booklendinfo leandinfo= new booklendinfo();
		leandinfo.setUserid(userid);
		userbookinfo userbookinfo =new userbookinfo();
		for(int i=0;i<bookinfoList.size();i++) {
			bookid=bookinfoList.get(i).getBookid();
			leandinfo.setBookid(bookid);
			booklendinfo booklendinfo= bookleadinfoDAO.selectByUseridAndBookidCheckIsover(leandinfo);
			if(booklendinfo==null)
			{
				userbookinfoList.add(bookinfoList.get(i));
				
			}else {
				userbookinfo=bookinfoList.get(i);
				if(booklendinfo.getIsover()==3) {
					userbookinfo.setIsbrrow(2); //曾借阅
				}else {
					userbookinfo.setIsbrrow(1); //已结悦
				}
				userbookinfoList.add(userbookinfo);
				
			}
		}
		
		PageInfo pageinfo =new PageInfo();
		pageinfo.setCount(page.getTotal());
		pageinfo.setData(userbookinfoList);
		return pageinfo;
	}
	
	/**
	 * 管理员获取到的已经上架的所有图书   未实现
	 * @param query
	 * @return
	 */
	public Object supergetAllbookList (BookQuery query) {
		
		Page<Object> page =PageHelper.startPage(query.getPage(), query.getLimit());
		bookinfoDAO.userselectAllBookList(query.getBookclass(),query.getBookname());
		PageInfo pageinfo =new PageInfo();
		pageinfo.setCount(page.getTotal());
		pageinfo.setData(page.getResult());
		return pageinfo;
	}
	
	/**
	 * 用户借阅图书
	 * @param query
	 * @return
	 */
	public Object userleadbook (booklendinfo booklendinfo,bussinfo bussindfo) {
	
		try {
			//检查书籍是否曾经借阅过
			 booklendinfo booklendinfoDB =	bookleadinfoDAO.selectByUseridAndBookidCheckIsover(booklendinfo);
				if(booklendinfoDB!=null) {
					if(booklendinfoDB.getIsover()==1) {
						return new BaseResult(0,"您已经借阅该书籍,请勿重复借阅");
					}if(booklendinfoDB.getIsover()==3) {
						return new BaseResult(0,"您正在归还状态");
					}
				}
		int	blancestatue= investUtil.leadbookinfo(booklendinfo, bussindfo);
		if(blancestatue==0) {return new BaseResult(0,"您的余额不足，请先充值");}
		} catch (Exception e) {
			return new BaseResult(0,"书籍借阅异常");
		}
		return new BaseResult(200,"书籍借阅成功，请您图书馆自取");
	
	
	}
	/**
	 * 用户获取到所有的用户图书
	 * @param query
	 * @param userid
	 * @return
	 */
	public Object getUserLeadBookByUser( BookLendQuery query,Integer userid) {
		
		Page<Object> page=PageHelper.startPage(query.getPage(),query.getLimit());
		bookleadinfoDAO.userselectleadinfoByuserid(query.getBookname(),query.getBookclass(),userid);
		PageInfo pageinfo =new PageInfo();
		pageinfo.setCount(page.getTotal());
		pageinfo.setData(page.getResult());
		return pageinfo;
		
	}
	
	/**
	 * 用户获取到所有的用户图书  进行状态操作
	 * @param query
	 * @param userid
	 * @return
	 */
	public Object getUserLeadBookByUserstatue( BookLendQuery query,Integer userid) {
		
		Page<Object> page=PageHelper.startPage(query.getPage(),query.getLimit());
		bookleadinfoDAO.userselectleadinfoByuseridstatue(query.getBookname(),query.getBookclass(),userid);
		PageInfo pageinfo =new PageInfo();
		pageinfo.setCount(page.getTotal());
		pageinfo.setData(page.getResult());
		return pageinfo;
		
	}
	
	public Object userReturnBook(booklendinfo booklendinfo) {
		
		 booklendinfo booklendinfoDB =	bookleadinfoDAO.selectByPrimaryKey(booklendinfo.getActionid());
			if(booklendinfoDB!=null) {
				if(booklendinfoDB.getIsover()==0) {
					return new BaseResult(0,"您已经归还，请勿重复归还");
				}
			}
		 bookleadinfoDAO.updateByPrimaryKeySelective(booklendinfo);
		 return new BaseResult(0, "已提交申请，等待管理员确认");
	}
	

}
