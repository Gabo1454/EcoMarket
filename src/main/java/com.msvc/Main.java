package com.msvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.msvc.boleta.client")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

//Comunicacion entre microservicios
//CRED
//VALIDAR LA INFORMACION
//Update debe tener crud
//Delete en cascada
//Git obligatorio✅
