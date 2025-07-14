package com.msvc.cliente.controller;

import com.msvc.cliente.dtos.ClienteCreationDTO;
import com.msvc.cliente.dtos.ClienteEstadoDTO;
import com.msvc.cliente.dtos.ErrorDTO;
import com.msvc.cliente.models.entities.Cliente;
import com.msvc.cliente.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@Validated
@Tag(
        name = "Cliente API",
        description = "Aqui se generan todos los metodos CRUD para Cliente"
)

public class ClienteController {

    @Autowired
    public ClienteService clienteService;

    @PostMapping
    @Operation(
            summary = "Endpoint guardado de un cliente",
            description = "Endpoint que me permite capturar un elemento Cliente.class y lo guarda " +
                    "dentro de nuestra base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Creacion exitosa"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Algun elemento de un msvc no se encuentra",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "El elemento que intentas crear ya existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(contains = DataIntegrityViolationException.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Estructura de datos que me permite ralizar la creaion de un cliente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteCreationDTO.class)
            )
    )
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody ClienteCreationDTO clienteCreationDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteService.save(clienteCreationDTO));
    }

    @GetMapping
    @Operation(
            summary = "Endpoint que obtiene todos los clientes",
            description = "Este endpoint devuelve un List con todos los clientes que esten registrados " +
                    "en la base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion de extraccion de clientes exitosa"
            )
    })
    public ResponseEntity<List<Cliente>> traerTodos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Endpoint que devuelve un cliente por id",
            description = "Endpoint que va a devolver un elemento de Cliente al momento de buscarlo por su numero identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obtencion por id correcta"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error cuando el cliente con cierto ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(
                    name = "id",
                    description = "Primary Key - Entidad Cliente",
                    required = true
            )
    })
    public ResponseEntity<Cliente> traerCliente(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Endpoint que actualiza un cliente por id",
            description = "Endpoint que va a actualizar los parametros de un cliente " +
                    "al momento de buscarlo por ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizacion por ID exitosa"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error cuando el cliente con cierto ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(
                    name = "id",
                    description = "Primary Key - Entidad Cliente",
                    required = true
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Estructura de datos que me permite actualizar un cliente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteCreationDTO.class)
            )
    )
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteService.updateCliente(id, cliente));
    }

    @Operation(
            summary = "Endpoint que actualiza el estado de un cliente por id",
            description = "Endpoint que va a actualizar el parametro de estado de un cliente " +
                    "al momento de buscarlo por ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizacion de Estado por id Exitosa"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error cuando el Cliente con cierto ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(
                    name = "id",
                    description = "Primary Key - Entidad Cliente",
                    required = true
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Estructura de datos que me permite actualizar el estado de un cliente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteEstadoDTO.class)
            )
    )
    @PutMapping({"/estado/{id}", "/estado/{id}"})
    public ResponseEntity<Cliente> actualizarEstadoCliente(@PathVariable Long id, @Valid @RequestBody ClienteEstadoDTO clienteEstadoDetails) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.actualizarEstadoCliente(id, clienteEstadoDetails));
    }

}
