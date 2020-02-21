package com.dfyz.provied.yiliutochain.dao;

import com.dfyz.provied.yiliutochain.po.GPSyiliu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GPSyiliuDao {
    int saveToChain(GPSyiliu gpSyiliu);
}
