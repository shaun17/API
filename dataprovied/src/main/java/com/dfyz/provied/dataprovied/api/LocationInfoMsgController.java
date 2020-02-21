package com.dfyz.provied.dataprovied.api;

import com.dfyz.provied.dataprovied.service.LocationInfoMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class LocationInfoMsgController {
    @Autowired
    LocationInfoMsgService locationInfoMsgService;

    @PostMapping("/gps/queryList")
    public Object queryList(@RequestBody Map params, HttpServletRequest request){
        System.out.println(request.getRequestURL());
        System.out.println(params);
        System.out.println(request.getHeader("Authorization"));
        System.out.println( params.get("plateNumber"));
        List list = locationInfoMsgService.queryList(params);
        return list;

    }
}
