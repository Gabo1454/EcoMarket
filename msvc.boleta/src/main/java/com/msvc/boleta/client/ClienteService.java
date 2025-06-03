package com.msvc.boleta.client;

import com.msvc.boleta.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@FeignClient
@Service
public class ClienteService {

    //Esto es lo que estás inyectando
    private final ClienteClient clienteClient;

    // Constructor con inyección
    @Autowired
    public ClienteService(ClienteClient clienteClient) {
        this.clienteClient = clienteClient;
    }

    //Método que usa el Feign Client
    public Cliente obtenerClientePorId(Long id) {
        return clienteClient.obtenerClientePorId(id);
    }
}
