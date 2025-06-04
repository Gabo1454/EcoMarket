package com.msvc.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDTO {

    private Long id;
    private Integer stock;
    private LocalDate fecha;
}
