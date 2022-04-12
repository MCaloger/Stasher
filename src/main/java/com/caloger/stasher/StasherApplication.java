package com.caloger.stasher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StasherApplication {
	public static void main(String[] args) {
		SpringApplication.run(StasherApplication.class, args);
	}

}
