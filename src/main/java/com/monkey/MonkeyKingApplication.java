package com.monkey;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan(basePackages = { "com.monkey.**.dao", "com.monkey.**.mapper" })
@EnableScheduling
@SpringBootApplication
public class MonkeyKingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonkeyKingApplication.class, args);
	}

}
