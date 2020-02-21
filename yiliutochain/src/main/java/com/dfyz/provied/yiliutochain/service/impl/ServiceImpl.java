package com.dfyz.provied.yiliutochain.service.impl;

import com.dfyz.provied.yiliutochain.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImpl {
    @Autowired
    Dao dao;

    public List query(){
        return dao.queryList();
    }
    public int savaToChain(){
        return dao.saveToChain();
    }
}
