package com.msvc.cliente.service;

import com.msvc.cliente.exceptions.ClienteException;
import com.msvc.cliente.models.entities.Cliente;
import com.msvc.cliente.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Obtener todos los clientes
    @Override
    public List<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }

    // Buscar cliente por ID
    @Override
    public Cliente findById(Long id) {
        return this.clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException("Cliente no encontrado en la base de datos"));
    }

    // Guardar nuevo cliente
    @Override
    public Cliente save(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    // Actualizar cliente existente
    @Override
    public Cliente update(Long id, Cliente cliente) {
        Cliente existente = this.clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException("No se puede actualizar. Cliente con ID " + id + " no existe."));

        // Actualiza los campos necesarios
        existente.setNombre(cliente.getNombre());
        // Agrega más campos si tu entidad Cliente tiene otros atributos (correo, teléfono, etc.)

        return this.clienteRepository.save(existente);
    }

    // Eliminar cliente por ID
    @Override
    public void deleteById(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteException("No se puede eliminar. Cliente con ID " + id + " no existe.");
        }
        clienteRepository.deleteById(id);
    }
}