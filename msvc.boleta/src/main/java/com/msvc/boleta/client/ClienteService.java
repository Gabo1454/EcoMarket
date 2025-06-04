package com.msvc.boleta.client;

import com.msvc.boleta.models.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteClient clienteClient;

    public ClienteService(ClienteClient clienteClient) {
        this.clienteClient = clienteClient;
    }

    public Cliente obtenerClientePorId(Long id) {
        return clienteClient.obtenerClientePorId(id);
    }
}
