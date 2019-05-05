package com.capgemini.gamecapmates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@SpringBootApplication
@ContextConfiguration(classes = AppConfig.class)
public class GameCapMatesBoardApplication {
	public static void main(String[] args) {
		SpringApplication.run(GameCapMatesBoardApplication.class, args);
	}
}
