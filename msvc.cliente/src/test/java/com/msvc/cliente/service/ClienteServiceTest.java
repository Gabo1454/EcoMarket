package com.msvc.cliente.service;

import com.msvc.cliente.exceptions.ClienteException;
import com.msvc.cliente.models.entities.Cliente;
import com.msvc.cliente.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void guardarCliente() {
        Cliente cliente = new Cliente(0L, "12345678-9", "Gabriel", "+56912345678");
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente guardado = clienteService.save(cliente);

        assertThat(guardado.getNombre()).isEqualTo("Gabriel");
        verify(clienteRepository).save(cliente);
    }

    @Test
    void buscarClientePorIdExistente() {
        Cliente cliente = new Cliente(1L, "12345678-9", "Gabriel", "+56912345678");
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente encontrado = clienteService.findById(1L);

        assertThat(encontrado.getRut()).isEqualTo("12345678-9");
        verify(clienteRepository).findById(1L);
    }

    @Test
    void buscarClientePorIdNoExistente() {
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ClienteException.class, () -> clienteService.findById(99L));
    }

    @Test
    void eliminarClienteExistente() {
        when(clienteRepository.existsById(1L)).thenReturn(true);

        clienteService.deleteById(1L);

        verify(clienteRepository).deleteById(1L);
    }

    @Test
    void eliminarClienteNoExistente() {
        when(clienteRepository.existsById(999L)).thenReturn(false);

        assertThrows(ClienteException.class, () -> clienteService.deleteById(999L));
    }
}