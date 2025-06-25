package com.msvc.boleta.service;

import com.msvc.boleta.models.entities.Boleta;
import com.msvc.boleta.repositories.BoletaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class BoletaServiceTest {

    @Mock
    private BoletaRepository boletaRepository;

    @InjectMocks
    private BoletaServiceImpl boletaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void guardarBoleta_exito() {
        Boleta boleta = new Boleta(null, 2L, LocalDate.now(), 10000.0);
        when(boletaRepository.save(boleta)).thenReturn(new Boleta(1L, 2L, boleta.getFechaBoleta(), 10000.0));

        Boleta resultado = boletaService.save(boleta);

        assertThat(resultado.getIdBoleta()).isEqualTo(1L);
        assertThat(resultado.getTotal()).isEqualTo(10000.0);
        verify(boletaRepository, times(1)).save(boleta);
    }

    @Test
    void buscarPorId_boletaExiste() {
        Boleta boleta = new Boleta(1L, 2L, LocalDate.now(), 10000.0);
        when(boletaRepository.findById(1L)).thenReturn(Optional.of(boleta));

        Boleta resultado = boletaService.findById(1L);

        assertThat(resultado.getIdCliente()).isEqualTo(2L);
    }
}