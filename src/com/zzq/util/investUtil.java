package com.zzq.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzq.common.BaseResult;
import com.zzq.entity.booklendinfo;
import com.zzq.entity.bussinfo;
import com.zzq.entity.invest;
import com.zzq.entity.investnote;
import com.zzq.entity.schoolbusorder;
import com.zzq.entity.userinfo;
import com.zzq.mapper.booklendinfoMapper;
import com.zzq.mapper.bussinfoMapper;
import com.zzq.mapper.investMapper;
import com.zzq.mapper.investnoteMapper;
import com.zzq.mapper.schoolbusorderMapper;
import com.zzq.mapper.userinfoMapper;
/**
 * 命名有误，实际上该类封装了所有对于金钱的操作
 * @author EDZ
 *
 */
@Service
public class investUtil {
	@Autowired
	investMapper investDAO;
	@Autowired
	investnoteMapper investnoteDAO;
	@Autowired
	userinfoMapper userinfoDAO;
	@Autowired
	booklendinfoMapper bookleadinfoDAO;
	@Autowired
	bussinfoMapper bussinfoDao;
	@Autowired
	schoolbusorderMapper schoolorderDao;
	
	/**
	 * 出现异常回滚  充值核心方法
	 * @param count
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public  Object investDB(Double count,Integer userid)  {
		
		invest invest=  investDAO.selectByUserId(userid);
		Double blanceInteger= invest.getBalance();
		Double otherInteger =blanceInteger+count;
			Date date=new Date();
			//创建 充值记录实体类
			investnote note = new investnote();
			note.setInvesttime(date);
			note.setInvestcount(count);
			note.setUserid(userid);
			note.setCount(otherInteger);
			
			// 写入消费业务状态
			writebuessinfo(date,count,otherInteger,userid);
			//更新充值记录状态
			investnoteDAO.insert(note);
			//更新余额
			changeblance(userid,otherInteger);
		return new BaseResult(200, "充值成功");
	}
	/**
	 * 充值时数据用户校验  然后再进行 返回充值结果
	 * @param userid
	 * @param username
	 * @param investcount
	 * @return
	 */
	public  Object investcountCore(Integer userid,String username,Double investcount) {
		
	    userinfo userinfoDB=	userinfoDAO.selectByPrimaryKey(userid);
	    if(investcount<=0) {
	    	
	    	return new BaseResult(0,"充值金额不应该小于0");
	    }
	    if(userinfoDB.getUsername().equals(username)) {
	    try {
	    	return	investDB(investcount,userid);
		} catch (Exception e) {
			return new BaseResult(0, "充值数据状态异常");
		}	
	    }else {
	    	return new BaseResult(0,"用户名和用户id不对应");
	    }
	}
	
	public void writebuessinfo(Date date,Double actionmoney,Double blance,Integer userid) {
		bussinfo bussinfo = new bussinfo();
		bussinfo.setActiontime(date);
		bussinfo.setActionmoney(actionmoney);
		bussinfo.setBlance(blance);
		bussinfo.setUserid(userid);
		bussinfo.setLocalid(6);
		bussinfoDao.insert(bussinfo);
	}
	public int leadbookinfo(booklendinfo booklendinfo,bussinfo bussindfo) {
		
		Date date=new Date();
		Double price= bussindfo.getActionmoney();
		
		invest invest=  investDAO.selectByUserId(booklendinfo.getUserid());
		Double endblance = invest.getBalance()-price;
		if(endblance<0) {return 0;}
		bussindfo.setUserid(booklendinfo.getUserid());
		bussindfo.setActiontime(date);
		bussindfo.setBlance(endblance);
		bussindfo.setLocalid(5);
		
		booklendinfo.setIsover(1);//正在借阅设置为1
		booklendinfo.setLendtime(date);
		bussinfoDao.insert(bussindfo);
		bookleadinfoDAO.insert(booklendinfo);
		changeblance(booklendinfo.getUserid(),endblance);
		return 200;
	}
	/**
	 * 改变余额
	 * @param userid
	 * @param overblance
	 */
	public void changeblance(Integer userid,Double overblance) {
		invest overInvest= new invest();
		overInvest.setUserid(userid);
		overInvest.setBalance(overblance);
		//更新余额
		investDAO.updateinvest(overInvest);
	}
	/**
	 * 用户进行 线路预定
	 */
	public int subline(schoolbusorder schoolbusorder,bussinfo bussindfo) {
		

		Date date=new Date();
		Double price= bussindfo.getActionmoney();
		
		invest invest=  investDAO.selectByUserId(schoolbusorder.getUserid());
		Double endblance = invest.getBalance()-price;
		if(endblance<0) {return 0;}
		bussindfo.setUserid(schoolbusorder.getUserid());
		bussindfo.setActiontime(date);
		bussindfo.setBlance(endblance);
		bussindfo.setLocalid(3);
		
		schoolbusorder.setIssuccess(1);//正在预定状态为1
		schoolbusorder.setOrdertime(date);//设置提交时间
		bussinfoDao.insert(bussindfo);
		schoolorderDao.insert(schoolbusorder);
		changeblance(schoolbusorder.getUserid(),endblance);
		return 200;
	}
	
}
