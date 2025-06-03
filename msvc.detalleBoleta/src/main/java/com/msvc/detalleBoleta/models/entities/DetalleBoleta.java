package com.msvc.detalleBoleta.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="detalleBoletas")
@Getter @Setter @ToString //Genera automáticamente los métodos get y set para todos los atributos.
@NoArgsConstructor //Crea un constructor vacío (sin parámetros). Necesario para que JPA pueda crear objetos.
@AllArgsConstructor //Crea un constructor con todos los atributos como parámetros.
public class DetalleBoleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleBoleta;

    private Long idBoleta; //esta debe ser foreign

    @NotNull(message = "Debe incluir id producto")
    private Long idProducto;

    @NotNull(message = "Debe incluir la cantidad de productos vendidos")
    @Min(value = 1, message = "La cantidad mínima es 1")
    private Integer cantidad;

    @NotNull(message = "Debe incluir el precio unitario")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private Double precioUnidad;

    @NotNull(message = "Debe incluir el subtotal")
    @DecimalMin(value = "0.0", inclusive = false, message = "El subtotal debe ser mayor a 0")
    private Double subTotal;


}
