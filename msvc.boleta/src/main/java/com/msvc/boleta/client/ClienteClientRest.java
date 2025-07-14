package com.msvc.boleta.client;

import com.msvc.boleta.dto.ClienteResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-clientes", url = "http://localhost:8000") // o solo name si usas Eureka
public interface ClienteClientRest {

    @GetMapping("/api/v1/clientes/{id}")
    ClienteResponseDTO findClienteById(@PathVariable Long id); //devuelve ClienteResponseDTO
}
