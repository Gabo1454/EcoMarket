package com.msvc.boleta.service;

import com.github.javafaker.Faker;
import com.msvc.boleta.client.ClienteClientRest;
import com.msvc.boleta.dto.BoletaDTO;
import com.msvc.boleta.dto.BoletaResponseDTO;
import com.msvc.boleta.dto.ClienteResponseDTO;
import com.msvc.boleta.exception.ResourceNotFoundException;
import com.msvc.boleta.models.entities.Boleta;
import com.msvc.boleta.repositories.BoletaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

//Activar Integfracion de mockito y Junit
@ExtendWith(MockitoExtension.class)
public class BoletaServiceTest {

    @Mock
    private BoletaRepository boletaRepository;

    @Mock
    private ClienteClientRest clienteClientRest;

    private List<Boleta> boletaList = new ArrayList<>();

    private Boleta boletaPrueba;

    @InjectMocks
    private BoletaServiceImpl boletaService;

    @BeforeEach
    public void setUp() {
        Faker faker = new Faker(Locale.of("es", "CL"));
        for(int i=0;i<100;i++){
            Boleta boleta = new Boleta();
            boleta.setIdBoleta((long) i+1);
            boleta.setFechaBoleta(LocalDate.now().minusDays(faker.number().numberBetween(0, 90)));
            boleta.setTotal(faker.number().randomDouble(2, 1000, 50000));
            boleta.setDescripcionBoleta("Boleta por compra de " + faker.commerce().productName());
            boleta.setIdClientePojo((long) faker.number().numberBetween(1, 50));
            this.boletaList.add(boleta);
        }
        this.boletaPrueba = this.boletaList.get(0);
    }

    @Test
    @DisplayName("Debe crear una boleta correctamente")
    public void crearBoletaCuandoClienteExiste() {
        BoletaDTO dto = new BoletaDTO("Compra test", 1L);
        ClienteResponseDTO cliente = new ClienteResponseDTO();
        cliente.setIdUsuario(1L);

        when(clienteClientRest.findClienteById(1L)).thenReturn(cliente);
        when(boletaRepository.save(any(Boleta.class))).thenAnswer(i -> {
            Boleta b = i.getArgument(0);
            b.setIdBoleta(100L);
            return b;
        });

        BoletaResponseDTO resultado = boletaService.crearBoleta(dto);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getIdBoleta()).isEqualTo(100L);
        assertThat(resultado.getCliente().getIdUsuario()).isEqualTo(1L);

        verify(clienteClientRest, times(1)).findClienteById(1L);
        verify(boletaRepository, times(1)).save(any(Boleta.class));
    }

    @Test
    @DisplayName("Debe obtener boleta por id")
    public void DebeObtenerBoletaPorIdCuandoExiste() {
        when(boletaRepository.findById(1L)).thenReturn(Optional.of(boletaPrueba));
        ClienteResponseDTO cliente = new ClienteResponseDTO();
        cliente.setIdUsuario(1L);
        when(clienteClientRest.findClienteById(anyLong())).thenReturn(cliente);

        BoletaResponseDTO resultado = boletaService.obtenerBoletaPorId(1L);
    }

    @Test
    @DisplayName("Debe lanzar una excepcion si el id no existe")
    public void DebeLanzarExcepcionSiElIdNoExiste() {
        Long idInexistente = 99L;
        when(boletaRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> boletaService.obtenerBoletaPorId(idInexistente))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Boleta no encontrada con ID: " + idInexistente);
    }

    @Test
    @DisplayName("Obtener todas las boletas con sus Clientes")
    public void obtenerTodasLasBoletasOmiteBoletasConClienteInvalido() {
        when(boletaRepository.findAll()).thenReturn(boletaList);
        when(clienteClientRest.findClienteById(anyLong())).thenReturn(new ClienteResponseDTO());

        List<BoletaResponseDTO> resultado = boletaService.obtenerTodas();

        assertThat(resultado).hasSize(100);

        verify(boletaRepository, times(1)).findAll();
        verify(clienteClientRest, times(100)).findClienteById(anyLong());
    }

    @Test
    @DisplayName("Retorna lista vacia si no hay boletas registradas")
    public void debeRetornarListaVaciaSiNoHayBoletas() {
        when(boletaRepository.findAll()).thenReturn(new ArrayList<>());

        List<BoletaResponseDTO> resultado = boletaService.obtenerTodas();

        assertThat(resultado).isEmpty();
        verify(boletaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Obtener todas las boletas de un ID Cliente")
    public void obtenerBoletasPorIdClienteCuandoExiste() {

        Long clienteId = 10L;

        //Se crean las boletas asociadas a ese Cliente
        List<Boleta> boletasCliente = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Boleta b = new Boleta();
            b.setIdBoleta((long) i + 1);
            b.setDescripcionBoleta("Producto Ejemplo " + i);
            b.setIdClientePojo(clienteId);
            b.setTotal(1000.0);
            boletasCliente.add(b);
        }
        ClienteResponseDTO cliente = new ClienteResponseDTO();
        cliente.setIdUsuario(clienteId);
        when(clienteClientRest.findClienteById(clienteId)).thenReturn(cliente);
        when(boletaRepository.findByIdClientePojo(clienteId)).thenReturn(boletasCliente);

        List<BoletaResponseDTO> resultado = boletaService.obtenerPorCliente(clienteId);

        assertThat(resultado).hasSize(3);
        assertThat(resultado.get(0).getCliente().getIdUsuario()).isEqualTo(clienteId);

        verify(clienteClientRest, times(1)).findClienteById(clienteId);
        verify(boletaRepository, times(1)).findByIdClientePojo(clienteId);
    }

    @Test
    @DisplayName("Lanza una excepcion cuando el ID de cliente no existe")
    public void DebeLanzarExcepcionCuandoElIdClienteNoExisteAlObtenerBoletas() {

        Long clienteIdInexistente = 999L;


        //Se simula que el servicio cliente lanza una excepcion
        when(clienteClientRest.findClienteById(clienteIdInexistente))
                .thenThrow(new ResourceNotFoundException("Cliente no encontrado"));

        assertThatThrownBy(() -> boletaService.obtenerPorCliente(clienteIdInexistente))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Cliente no encontrado");

        verify(clienteClientRest, times(1)).findClienteById(clienteIdInexistente);
        verify(boletaRepository, never()).findByIdClientePojo(anyLong());
    }

    @Test
    @DisplayName("Debe actualizar el total de Boleta por ID cuando existe.")
    public void debeActualizarTotalBoletaPorIdCuandoExiste() {
        Boleta boleta = new Boleta();
        boleta.setIdBoleta(1L);
        boleta.setTotal(100.0);

        when(boletaRepository.findById(1L)).thenReturn(Optional.of(boleta));

        boletaService.actualizarTotalBoleta(1L, 50.0);

        verify(boletaRepository, times(1)).findById(1L);
        verify(boletaRepository).save(argThat(b -> b.getTotal() == 150.0));
    }

    @Test
    @DisplayName("Lanza excepcion si la boleta no existe al intentar actualizar el total")
    public void lanzaExcepcionAlActualizarBoletaNoExiste() {
        Long idInexistente = 99L;
        when(boletaRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> boletaService.actualizarTotalBoleta(idInexistente, 50.0))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Boleta no encontrada con ID: "+ idInexistente + " para actualizar total");
        verify(boletaRepository, times(1)).findById(idInexistente);
        verify(boletaRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe eliminar Boleta por ID cuando existe")
    public void debeEliminarBoletaPorIdCuandoExiste() {
        when(boletaRepository.existsById(1L)).thenReturn(true);

        boletaService.eliminarBoleta(1L);

        verify(boletaRepository, times(1)).existsById(1L);
        verify(boletaRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Lanza una excepcion si boleta no existe al eliminar")
    public void lanzaExcepcionSiBoletaNoExisteAlEliminar() {
        Long idInexistente = 99L;
        when(boletaRepository.existsById(idInexistente)).thenReturn(false);
        assertThatThrownBy(() -> boletaService.eliminarBoleta(idInexistente))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Boleta con ID " + idInexistente + " no encontrada para eliminar");

        verify(boletaRepository, times(1)).existsById(idInexistente);
        verify(boletaRepository, never()).deleteById(idInexistente);
    }

}