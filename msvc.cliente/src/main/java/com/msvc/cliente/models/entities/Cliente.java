package com.msvc.cliente.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import jakarta.persistence.*;
import lombok.*;

import java.util.Random;

@Data
@Entity
@Table(name = "clientes")
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idUsuario;

    @Column(nullable = false, name = "nombre_cliente")
    private String nombreCliente;

    @Column(nullable = false, name = "apellido_cliente")
    private String apellidoCliente;

    @Column(nullable = false, unique = true, name = "correo_cliente")
    private String correoCliente;

    @Column(nullable = false, unique = true, name = "contrasenia_cliente")
    private String contraseniaCliente;

    @Column(nullable = false, name = "direccion_envio_cliente")
    private String direccionEnvioCliente;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(nullable = false, name = "telefono_cliente")
    private String telefonoCliente;

}
