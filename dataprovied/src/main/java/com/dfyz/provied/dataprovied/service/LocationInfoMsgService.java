package com.dfyz.provied.dataprovied.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LocationInfoMsgService {
    List<Map<String,Object>> queryList( Map<String,Object> map);

}
