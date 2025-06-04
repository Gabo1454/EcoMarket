package com.msvc.boleta.dto;

import com.msvc.boleta.models.entities.Boleta;
import lombok.Data;

import java.util.List;

@Data
public class BoletaDetalleDTO {

    private Boleta boleta;
    private List<DetalleBoletaDTO> detalles;

}
