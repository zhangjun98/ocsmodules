package com.zzq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zzq.entity.shoolbusinfo;
import com.zzq.entity.userschoolbusinfo;
@Repository
public interface shoolbusinfoMapper {
    int deleteByPrimaryKey(Integer lineid);

    int insert(shoolbusinfo record);

    int insertSelective(shoolbusinfo record);

    shoolbusinfo selectByPrimaryKey(Integer lineid);
    //完成
    List<userschoolbusinfo>  userselectAllbusList(@Param("bustart") String start,@Param("busend") String end);
    
    
    int updateByPrimaryKeySelective(shoolbusinfo record);

    int updateByPrimaryKey(shoolbusinfo record);
}