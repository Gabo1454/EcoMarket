package com.msvc.cliente.service;

import com.msvc.cliente.dtos.ClienteCreationDTO;
import com.msvc.cliente.exceptions.ClienteException;
import com.msvc.cliente.models.entities.Cliente;
import com.msvc.cliente.repositories.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    /**
     * Crea un objeto Cliente en el Sistema
     *
     * @param clienteDetails datos de Cliente a crear
     * @throws org.springframework.dao.DataIntegrityViolationException si la contraseña o el correo ya existen
     */
    @Transactional
    @Override
    public Cliente crearCliente(ClienteCreationDTO clienteDetails) {

        boolean existe = clienteRepository.existsByCorreoClienteAndContraseniaCliente(
                clienteDetails.getCorreoCliente(), clienteDetails.getContraseniaCliente());

        if (existe) {
            throw new ClienteException("Ya existe un cliente con ese correo y contraseña.");
        }

        Cliente clienteEntity = new Cliente();
        clienteEntity.setNombreCliente(clienteDetails.getNombreCliente());
        clienteEntity.setApellidoCliente(clienteDetails.getApellidoCliente());
        clienteEntity.setCorreoCliente(clienteDetails.getCorreoCliente());
        clienteEntity.setContraseniaCliente(clienteDetails.getContraseniaCliente());
        clienteEntity.setDireccionEnvioCliente(clienteDetails.getDireccionEnvioCliente());

        return clienteRepository.save(clienteEntity);
    }

    /**
     * Obtiene una List con todos los Clientes registrados
     *
     * @return Lista de Cliente con todos los Clientes
     * @throws ClienteException si no hay clientes registrados
     */
    @Transactional(readOnly = true)
    @Override
    public List<Cliente> traerTodos() {

        List<Cliente> clientes = clienteRepository.findAll();

        if (clientes.isEmpty()) {
            throw new ClienteException("No hay clientes registrados");
        }

        return clientes;
    }

    /**
     * Obtiene un elemento Cliente por ID
     *
     * @param id ID del Cliente
     *
     * @return retorna un elemento Cliente con el ID proporcionado
     * @throws ClienteException si el ID del Cliente no existe
     */
    @Transactional(readOnly = true)
    @Override
    public Cliente traerPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new ClienteException("Cliente con id " + id + " no encontrado")
        );
    }

    /**
     * Actualiza los datos de Cliente por su ID
     *
     * @param idCliente ID del Cliente
     * @param clienteDetails detalles de Cliente que se usaran para Actuzalizar
     * @throws ClienteException si el ID del Cliente no existe
     */
    @Transactional @Override
    public Cliente actualizarCliente(Long idCliente, Cliente clienteDetails){
        return clienteRepository.findById(idCliente).map(cliente -> {
            cliente.setNombreCliente(clienteDetails.getNombreCliente());
            cliente.setApellidoCliente(clienteDetails.getApellidoCliente());
            cliente.setCorreoCliente(clienteDetails.getCorreoCliente());
            cliente.setContraseniaCliente(clienteDetails.getContraseniaCliente());
            cliente.setDireccionEnvioCliente(clienteDetails.getDireccionEnvioCliente());
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new ClienteException("Cliente con id " + idCliente + " no encontrado"));
    }

    /**
     * Elimina un elemento Cliente por su ID
     *
     * @param id ID del Cliente
     * @throws ClienteException si el ID del Cliente no existe
     */
    @Transactional @Override
    public void eliminarCliente(Long id){

        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isEmpty()) {
            throw new ClienteException("No se pudo eliminar: Cliente con id " + id + " no encontrado");
        }

        clienteRepository.deleteById(id);
    }

    /**
     * Actualiza el estado de un elemento Cliente
     *
     * @param id ID del Cliente
     * @throws ClienteException si el ID del Cliente no existe
     */
    @Transactional @Override
    public Cliente actualizarEstadoCliente(Long id, ClienteEstadoDTO clienteEstadoDetails){
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setActivo(clienteEstadoDetails.getActivo());
            log.info("Estado actualizado: {} {}", clienteEstadoDetails.getActivo(), cliente);
            return clienteRepository.save(cliente);

        }).orElseThrow(() -> new ClienteException("Cliente con id " + id + " no encontrado"));
    }


}
}