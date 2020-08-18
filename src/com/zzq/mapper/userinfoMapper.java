package com.zzq.mapper;

import org.springframework.stereotype.Repository;

import com.zzq.entity.userinfo;

@Repository
public interface userinfoMapper {
	//
    int deleteByPrimaryKey(Integer userid);
    //
    int insert(userinfo record);
    //
    int insertSelective(userinfo record);
    //
    userinfo selectByPrimaryKey(Integer userid);
    
    userinfo selectByUsername(String username);
    //
    int updateByPrimaryKeySelective(userinfo record);
    //
    int updateByPrimaryKey(userinfo record);
}