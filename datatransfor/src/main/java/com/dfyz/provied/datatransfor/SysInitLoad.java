package com.dfyz.provied.datatransfor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
* 启动启动时执行
*/
@Component
public class SysInitLoad implements ApplicationRunner {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String url = "http://localhost:8080/queryList";
        String key="abcdefg";
        String s = restTemplate.postForObject(url, key, String.class);
        System.out.println(s);
    }
}