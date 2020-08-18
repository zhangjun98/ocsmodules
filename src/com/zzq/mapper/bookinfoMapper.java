package com.zzq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zzq.entity.bookinfo;
import com.zzq.entity.userbookinfo;

@Repository
public interface bookinfoMapper {
	//
    int deleteByPrimaryKey(Integer bookid);
    //
    int insert(bookinfo record);
    //
    int insertSelective(bookinfo record);
    //
    bookinfo selectByPrimaryKey(Integer bookid);
    //
    int updateByPrimaryKeySelective(bookinfo record);
    //
    int updateByPrimaryKey(bookinfo record);
    
    List<userbookinfo> userselectAllBookList(@Param("classid") Integer classid,@Param("bookname")String bookname);
    
    List<bookinfo> superselectAllBookList(@Param("classid") Integer classid,@Param("bookname")String bookname);
    
}