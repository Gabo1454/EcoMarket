package com.msvc.boleta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta completa con datos de boleta y cliente")
public class BoletaResponseDTO {

    @Schema(description = "ID único de la boleta", example = "1")
    @JsonProperty("id_boleta")
    private Long idBoleta;

    @Schema(description = "Fecha de emisión de la boleta")
    @JsonProperty("fecha_emision")
    private LocalDate fechaEmisionBoleta;

    @Schema(description = "Total acumulado de la boleta", example = "1250.50")
    @JsonProperty("total_boleta")
    private Double totalBoleta;

    @Schema(description = "Descripción de la boleta")
    @JsonProperty("descripcion_boleta")
    private String descripcionBoleta;

    @Schema(description = "Información completa del cliente asociado")
    @JsonProperty("cliente")
    private ClienteResponseDTO cliente;

}
