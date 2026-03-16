package com.app.alpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class AlphaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlphaApplication.class, args);

//		Info info = new Info();
//		info.resumenVideo("https://www.youtube.com/watch?v=WoB6-Sl64kI&list=WL");

	}

}
