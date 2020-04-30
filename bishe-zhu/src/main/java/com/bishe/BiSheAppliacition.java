package com.bishe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.bishe.mapper")
public class BiSheAppliacition {
    public static void main(String[] args) {
        SpringApplication.run(BiSheAppliacition.class);
    }
}
