package com.dfyz.provied.dataprovied.service.impl;

import com.dfyz.provied.dataprovied.dao.LocationInfoMsgDao;
import com.dfyz.provied.dataprovied.service.LocationInfoMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LocationInfoMsgServiceImpl implements LocationInfoMsgService {
    @Autowired
    LocationInfoMsgDao locationInfoMsg;
    @Override
    public List<Map<String, Object>> queryList(Map<String, Object> map) {
        return locationInfoMsg.queryList(map);
    }
}
