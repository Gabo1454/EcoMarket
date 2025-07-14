package com.msvc.cliente.service;

import com.msvc.cliente.dtos.ClienteCreationDTO;
import com.msvc.cliente.dtos.ClienteEstadoDTO;
import com.msvc.cliente.models.entities.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {

    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente save(ClienteCreationDTO clienteDetails);
    Cliente update(Long id, Cliente cliente);
    void deleteById(Long id);
    Cliente actualizarEstadoCliente(Long id, ClienteEstadoDTO clienteEstadoDetails);

}
