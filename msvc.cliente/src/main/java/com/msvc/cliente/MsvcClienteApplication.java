package com.msvc.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcClienteApplication.class, args);
	}

}
