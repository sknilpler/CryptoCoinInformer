package com.sknilpler.cryptocoininformer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CryptoCoinInformerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoCoinInformerApplication.class, args);
		System.out.println("To connect to swagger: http://[ip]:[port]/swagger-ui/index.html");
		System.out.println("http://localhost:8080/swagger-ui/index.html");
	}

}
