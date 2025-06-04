package com.msvc.boleta.client;

import com.msvc.boleta.models.Cliente; // Asegúrate de tener esta clase o crea un DTO
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-cliente", url = "http://localhost:8081") // o solo name si usas Eureka
public interface ClienteClient {

    @GetMapping("/api/clientes/{id}")
    Cliente obtenerClientePorId(@PathVariable Long id);
}
