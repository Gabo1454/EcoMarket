package com.msvc.cliente.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity //Indica que la clase es una entidad JPA, osea una tabla de base de datos spring/jpa. Se usa para mapear objetos JAVA a registros en la tabla cliente
@Table(name="clientes") //Define el nombre real de la tabla en la base de datos
@Getter @Setter @ToString //Genera automáticamente los métodos get y set para todos los atributos.
@NoArgsConstructor //Crea un constructor vacío (sin parámetros). Necesario para que JPA pueda crear objetos.
@AllArgsConstructor //Crea un constructor con todos los atributos como parámetros.
public class Cliente {
    @Id //Marca el campo como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indica que el valor del ID se autogenera en la BD
    private long idCliente;

    @NotBlank(message = "El campo rut esta vacio " )
    @Pattern(regexp = "^\\d{7,8}-[\\dkK]$", message = "El RUT debe tener un formato válido, EJ XXXXXXXX-X")
    @Column (nullable = false, unique = true)
    private String rut;

    @NotBlank(message = "El campo nombre no puede estar vacio")
    @Column (nullable = false)
    private String nombre;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^\\+?\\d{9,15}$", message = "Formato de teléfono inválido. Ej: +56912345678")
    @Column(nullable = false, length = 15)
    private String telefono;
}
