package com.msvc.boleta.models;

import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class ClientePojo {
    private Long id;
    private String nombre;
    private String telefono;
}