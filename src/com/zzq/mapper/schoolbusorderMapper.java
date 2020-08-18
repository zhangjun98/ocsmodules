package com.zzq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zzq.entity.schoolbusorder;
@Repository
public interface schoolbusorderMapper {
    int deleteByPrimaryKey(Integer actionid);
//更新
    int insert(schoolbusorder record);

   

    schoolbusorder selectByPrimaryKey(Integer actionid);
    // 完成
    schoolbusorder selectByUseridAndLineid(schoolbusorder record);
    
    List<schoolbusorder> selectByuserid(@Param("start")String start,@Param("end")String end,@Param("userid")Integer userid);
    
    List<schoolbusorder> selectALL(@Param("start")String start,@Param("end")String end);
    int updateByPrimaryKeySelective(schoolbusorder record);

    int updateByPrimaryKey(schoolbusorder record);
}