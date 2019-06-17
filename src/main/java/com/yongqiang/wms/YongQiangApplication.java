package com.yongqiang.wms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yongqiang.wms.mapper")
public class YongQiangApplication {

    public static void main(String[] args) {
        SpringApplication.run(YongQiangApplication.class, args);
    }

}
