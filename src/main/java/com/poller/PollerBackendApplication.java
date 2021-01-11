package com.poller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PollerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollerBackendApplication.class, args);
	}

}
