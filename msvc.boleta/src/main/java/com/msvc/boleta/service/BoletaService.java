package com.msvc.boleta.service;

import com.msvc.boleta.dto.BoletaDTO;
import com.msvc.boleta.dto.BoletaResponseDTO;
import com.msvc.boleta.models.entities.Boleta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoletaService {

    BoletaResponseDTO crearBoleta(BoletaDTO boletaDTO);
    List<BoletaResponseDTO> obtenerTodas();
    List<BoletaResponseDTO> obtenerPorCliente(Long idCliente);
    void eliminarBoleta(Long idFactura);
    void actualizarTotalBoleta(Long idFactura, Double monto);
    BoletaResponseDTO obtenerBoletaPorId(Long id);

}
