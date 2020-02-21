package com.dfyz.provied.dataprovied.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LocationInfoMsgDao {
    List<Map<String,Object>>  queryList(@Param("params") Map<String,Object> map);
}
