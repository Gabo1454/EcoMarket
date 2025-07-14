package com.msvc.boleta.dto;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long idCliente;
    private String nombre;
    private String telefono;
}
