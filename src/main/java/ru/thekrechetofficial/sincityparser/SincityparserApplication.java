package ru.thekrechetofficial.sincityparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SincityparserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SincityparserApplication.class, args);
	}

}
