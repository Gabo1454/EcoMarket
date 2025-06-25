package com.msvc.cliente.repositories;

import com.msvc.cliente.models.entities.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void guardarClienteYBuscarPorId() {
        Cliente cliente = new Cliente(0L, "12345678-9", "Gabriel", "+56912345678");
        Cliente guardado = clienteRepository.save(cliente);

        assertThat(guardado.getIdCliente()).isNotNull();
        assertThat(guardado.getNombre()).isEqualTo("Gabriel");
        assertThat(guardado.getRut()).isEqualTo("12345678-9");
    }

    @Test
    void eliminarCliente() {
        Cliente cliente = new Cliente(0L, "11111111-1", "Eliminar", "+56900000000");
        Cliente guardado = clienteRepository.save(cliente);

        clienteRepository.deleteById(guardado.getIdCliente());

        boolean existe = clienteRepository.findById(guardado.getIdCliente()).isPresent();
        assertThat(existe).isFalse();
    }
}