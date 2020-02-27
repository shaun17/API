package com.dfyz.provied.yiliutochain.controller;

import com.alibaba.fastjson.JSONObject;
import com.dfyz.provied.yiliutochain.common.CommonResponse;
import com.dfyz.provied.yiliutochain.po.GPSyiliu;
import com.dfyz.provied.yiliutochain.service.GPSyiliuService;
import org.apache.commons.lang3.StringUtils;
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
        try {
            JSONObject jsonObject = JSONObject.parseObject(JSON);
            JSONObject str = (JSONObject) jsonObject.get("data");
            GPSyiliu yiliu = (GPSyiliu) JSONObject.toJavaObject(str, GPSyiliu.class);
            if (yiliu == null) return new CommonResponse(500, false, "数据解析失败");
            String plateNumber = (String) jsonObject.get("ragname");
            String content = (String) jsonObject.get("content");
            if (StringUtils.isNotBlank(content)) yiliu.setContent(content);
            if (StringUtils.isNotBlank(plateNumber)) yiliu.setPlateNumber(plateNumber);
            int i = gpSyiliuService.saveToChain(yiliu);
            return (i > 0) ? new CommonResponse() : new CommonResponse(500, false, "数据接收失败");
        } catch (Exception e) {
            return new CommonResponse(500, false, "数据解析失败,请重新确定JSON参数");
        }

    }
}