package com.zzq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zzq.entity.classuser;
@Repository
public interface classinfoMapper {
	
	classuser selectByPrimaryKey(Integer classid);
	
	/**
	 * 用户仅仅通过 课程名称 进行查询所有用户的操作
	 * @param classid
	 * @return
	 */
	List<classuser> UserSelectAllClassList(@Param("classname") String classname);
	
}