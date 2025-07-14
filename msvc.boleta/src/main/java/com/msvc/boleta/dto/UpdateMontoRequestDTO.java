package com.msvc.boleta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para actualizar el monto de la boleta")
public class UpdateMontoRequestDTO {

    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser mayor a 0")
    private Double monto;

}
