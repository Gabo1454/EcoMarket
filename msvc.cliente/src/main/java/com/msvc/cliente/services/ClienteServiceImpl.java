package com.msvc.cliente.services;

import com.msvc.cliente.exception.ClienteException;
import com.msvc.cliente.models.entities.Cliente;
import com.msvc.cliente.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    //Select * from cliente
    @Override
    public List<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }
    //Select * from propiedad where id = parametro (id)
    @Override
    public Cliente findById(Long id) {
        return this.clienteRepository.findById(id).orElseThrow(
                ()-> new ClienteException("Cliente no encontrada en la base de datos")
        );
    }

    @Override
    public Cliente save(Cliente cliente) {
        Cliente propiedadEntity = new Cliente();
        propiedadEntity.setIdCliente(cliente.getIdCliente());
        propiedadEntity.setNombre(cliente.getNombre());
        return this.clienteRepository.save(cliente);
    }

    @Override
    public void deleteById(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteException("No se puede eliminar. Cliente con ID " + id + " no existe.");
        }
        clienteRepository.deleteById(id);
    }


}
