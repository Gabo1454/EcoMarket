package com.msvc.boleta.client;

import com.msvc.boleta.dto.ClienteResponseDTO;
import com.msvc.boleta.models.ClientePojo;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteClientRest clienteClientRest;

    public ClienteService(ClienteClientRest clienteClientRest) {
        this.clienteClientRest = clienteClientRest;
    }

    public ClienteResponseDTO findClienteById(Long id) {
        return clienteClientRest.findClienteById(id);
    }
}
