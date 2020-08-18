package com.zzq.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zzq.entity.invest;
import com.zzq.entity.userinfo;
@Repository
public interface investMapper {
	
	invest selectByUserId(@Param("userid") Integer userid) ;
	
	int updateinvest(invest invest);
}