package com.zzq.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zzq.entity.bussinfo;

@Repository
public interface bussinfoMapper {
	//
    int deleteByPrimaryKey(Integer actionid);
    
    int insert(bussinfo record);
  
    //
    bussinfo selectByPrimaryKey(Integer actionid);
    
    
   List<bussinfo>  userselectBybussinfo(bussinfo userid);
   List<bussinfo>  SuperuserselectALL(bussinfo bussinfo);
    //
    int updateByPrimaryKey(bussinfo record);
    
    int updateByUserdel(bussinfo record);
}