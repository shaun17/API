package com.dfyz.provied.yiliutochain.service.impl;

import com.dfyz.provied.yiliutochain.dao.GPSyiliuDao;
import com.dfyz.provied.yiliutochain.po.GPSyiliu;
import com.dfyz.provied.yiliutochain.service.GPSyiliuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GPSyiliuServiceImpl implements GPSyiliuService {
    @Autowired
    GPSyiliuDao dao;
    @Override
    public int saveToChain(GPSyiliu gpSyiliu) {
        return dao.saveToChain(gpSyiliu);
    }
}
