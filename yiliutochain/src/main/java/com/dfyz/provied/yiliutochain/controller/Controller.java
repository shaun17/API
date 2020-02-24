package com.dfyz.provied.yiliutochain.controller;

import com.alibaba.fastjson.JSONObject;
import com.dfyz.provied.yiliutochain.common.CommonResponse;
import com.dfyz.provied.yiliutochain.po.GPSyiliu;
import com.dfyz.provied.yiliutochain.service.GPSyiliuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    GPSyiliuService gpSyiliuService;

    @PostMapping("/dfyz/toChain")
    public CommonResponse toChain(@RequestBody String JSON) {
        JSONObject jsonObject = JSONObject.parseObject(JSON);
        GPSyiliu gps =  (GPSyiliu) JSONObject.toJavaObject(jsonObject, GPSyiliu.class);
        int i = gpSyiliuService.saveToChain(gps);
        return (i>0)?new CommonResponse():new CommonResponse(500,false,"数据接收失败");
    }
}
