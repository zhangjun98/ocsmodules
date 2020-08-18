package com.zzq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zzq.entity.booklendinfo;
@Repository
public interface booklendinfoMapper {
	//
    int deleteByPrimaryKey(Integer actionid);

    int insert(booklendinfo record);
    //
    int insertSelective(booklendinfo record);
    //
    booklendinfo selectByPrimaryKey(Integer actionid);
    //
    int updateByPrimaryKeySelective(booklendinfo record);
    //
    int updateByPrimaryKey(booklendinfo record);
    /**
     * 通过  userid 和 bookid查询用户记录  并且限制为 isover 不等于 0
     * @param record
     * @return
     */
    booklendinfo selectByUseridAndBookidCheckIsover(booklendinfo record);
    /**
     * 通过  userid 和 bookid查询用户记录 没有限制为 isover 不等于 0
     * @param record
     * @return
     */
    booklendinfo  selectByUseridAndBookid (booklendinfo record);
    
    /**
     * 通过用户id查询  该用户的 图书借阅信息  多表关联查询
     * @param actionid
     * @return
     */
    
    List<booklendinfo> userselectleadinfoByuserid(@Param("bookname")String bookname,@Param("bookclass")Integer bookclass,@Param("userid")Integer actionid);
    
    List<booklendinfo> userselectleadinfoByuseridstatue(@Param("bookname")String bookname,@Param("bookclass")Integer bookclass,@Param("userid")Integer actionid);

}