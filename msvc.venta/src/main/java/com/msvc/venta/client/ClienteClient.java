package com.msvc.venta.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente-client", url = "${cliente.service.url}")
public interface ClienteClient {

    @GetMapping("/api/clientes/{id}")
    Object obtenerClientePorId(@PathVariable Long id);

}
