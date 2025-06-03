package com.msvc.boleta.client;

import com.msvc.boleta.models.Cliente; // Asegúrate de tener esta clase o crea un DTO
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-cliente", url = "http://localhost:8002") // Usa el puerto real de msvc.cliente
public interface ClienteClient {

    @GetMapping("/clientes/{id}")
    Cliente obtenerClientePorId(@PathVariable("id") Long id);
}
