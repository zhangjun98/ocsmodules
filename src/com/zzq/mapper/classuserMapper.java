package com.zzq.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zzq.entity.classuser;

@Repository
public interface classuserMapper {
    int deleteByPrimaryKey(Integer actionid);

    int insert(classuser record);

    int insertSelective(classuser record);

    classuser selectByPrimaryKey(Integer actionid);
    
    
    classuser selectByClassidAndUserid(@Param("classid")Integer classid,@Param("userid")Integer userid);

    int updateByPrimaryKeySelective(classuser record);

    int updateByPrimaryKey(classuser record);
}