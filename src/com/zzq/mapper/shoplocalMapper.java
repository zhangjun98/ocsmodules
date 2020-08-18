package com.zzq.mapper;

import com.zzq.entity.shoplocal;

public interface shoplocalMapper {
    int deleteByPrimaryKey(Integer localid);

    int insert(shoplocal record);

    int insertSelective(shoplocal record);

    shoplocal selectByPrimaryKey(Integer localid);

    int updateByPrimaryKeySelective(shoplocal record);

    int updateByPrimaryKey(shoplocal record);
}