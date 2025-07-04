package com.msvc.cliente.service;

import com.msvc.cliente.models.entities.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {

    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    Cliente update(Long id, Cliente cliente);
    void deleteById(Long id);

}
