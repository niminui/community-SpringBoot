package com.nmh.community_nmh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nmh.community_nmh.mapper")
public class CommunityNmhApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityNmhApplication.class, args);
    }

}
