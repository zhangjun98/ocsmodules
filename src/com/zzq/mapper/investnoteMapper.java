package com.zzq.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zzq.entity.investnote;
@Repository
public interface investnoteMapper {
	//
    int deleteByPrimaryKey(Integer actionid);

    int insert(investnote record);
///
    int insertSelective(investnote record);
//
    investnote selectByPrimaryKey(Integer actionid);
/**
 * 逻辑删除
 * @param record
 * @return
 */
    int updateByPrimaryKeySelective(investnote record);
//
    int updateByPrimaryKey(investnote record);
    
    /**
     * 	进行搜索加载的
     * @param userid
     * @return
     */
    List<investnote> userselectByuseridAndCount(investnote investnote);
    
}