package com.msvc.boleta.repositores;

import com.msvc.boleta.models.entities.Boleta;
import com.msvc.boleta.repositories.BoletaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BoletaRepositoryTest {

    @Autowired
    private BoletaRepository boletaRepository;

    @Test
    void guardarYBuscarBoleta() {
        Boleta boleta = new Boleta(null, 1L, LocalDate.now(), 5000.0);
        Boleta guardada = boletaRepository.save(boleta);

        Optional<Boleta> encontrada = boletaRepository.findById(guardada.getIdBoleta());

        assertThat(encontrada).isPresent();
        assertThat(encontrada.get().getTotal()).isEqualTo(5000.0);
    }
}