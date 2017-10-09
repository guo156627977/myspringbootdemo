package com.gzq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gzq.mapper")
public class MyspringbootdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyspringbootdemoApplication.class, args);
	}
}
