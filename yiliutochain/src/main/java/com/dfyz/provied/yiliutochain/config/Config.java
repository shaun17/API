package com.dfyz.provied.yiliutochain.config;

import com.dfyz.provied.yiliutochain.common.CommonResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
        public CommonResponse commonResponse(){
        CommonResponse commonResponse = new CommonResponse();
        return commonResponse;
    }

}
