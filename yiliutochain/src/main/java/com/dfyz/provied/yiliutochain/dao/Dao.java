package com.dfyz.provied.yiliutochain.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface Dao {
    List queryList();
    int saveToChain();
}