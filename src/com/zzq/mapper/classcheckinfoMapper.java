package com.zzq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zzq.entity.classcheckinfo;
@Repository
public interface classcheckinfoMapper {
    int deleteByPrimaryKey(Integer actionid);

    int insert(classcheckinfo record);

    int insertSelective(classcheckinfo record);

    classcheckinfo selectByPrimaryKey(Integer actionid);
    
    List<classcheckinfo> selectByUseridAndCalssId(@Param("userid")Integer userid,@Param("classid")Integer classid);
    
    List<classcheckinfo> selectUserAllCheckList(@Param("classid")Integer classid,@Param("userid")Integer Userid);
    
    int updateByPrimaryKeySelective(classcheckinfo record);

   
}