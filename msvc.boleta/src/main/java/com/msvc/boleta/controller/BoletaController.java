package com.msvc.boleta.controller;

import com.msvc.boleta.client.ClienteService;
import com.msvc.boleta.dto.BoletaConDetalleDTO;
import com.msvc.boleta.models.entities.Boleta;
import com.msvc.boleta.service.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/boletas") // Puedes ajustar este path base si es necesario
public class BoletaController {

    private final ClienteService clienteService;

    @Autowired
    private BoletaService boletaService;

    public BoletaController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Boleta>>> getAll() {
        List<EntityModel<Boleta>> boletas = boletaService.findAll().stream()
                .map(b -> EntityModel.of(b,
                        linkTo(methodOn(BoletaController.class).getById(b.getIdBoleta())).withSelfRel(),
                        linkTo(methodOn(BoletaController.class).getCompleteBoleta(b.getIdBoleta())).withRel("detalle-completo")
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(boletas,
                        linkTo(methodOn(BoletaController.class).getAll()).withSelfRel())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Boleta>> getById(@PathVariable Long id) {
        Boleta boleta = boletaService.findById(id);

        EntityModel<Boleta> recurso = EntityModel.of(boleta,
                linkTo(methodOn(BoletaController.class).getById(id)).withSelfRel(),
                linkTo(methodOn(BoletaController.class).getAll()).withRel("todas-las-boletas"),
                linkTo(methodOn(BoletaController.class).getCompleteBoleta(id)).withRel("detalle-completo"),
                linkTo(methodOn(BoletaController.class).delete(id)).withRel("eliminar"),
                linkTo(methodOn(BoletaController.class).update(id, boleta)).withRel("actualizar")
        );

        return ResponseEntity.ok(recurso);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Boleta>> create(@RequestBody Boleta boleta) {
        Boleta creada = boletaService.save(boleta);

        EntityModel<Boleta> recurso = EntityModel.of(creada,
                linkTo(methodOn(BoletaController.class).getById(creada.getIdBoleta())).withSelfRel(),
                linkTo(methodOn(BoletaController.class).getAll()).withRel("todas-las-boletas")
        );

        return ResponseEntity.created(linkTo(methodOn(BoletaController.class).getById(creada.getIdBoleta())).toUri()).body(recurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boleta> update(@PathVariable Long id, @RequestBody Boleta boleta) {
        Boleta existing = boletaService.findById(id);
        existing.setIdCliente(boleta.getIdCliente());
        existing.setFechaBoleta(boleta.getFechaBoleta());
        existing.setTotal(boleta.getTotal());
        return ResponseEntity.ok(boletaService.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Boleta existing = boletaService.findById(id);
        boletaService.deleteById(existing.getIdBoleta());
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/complete")
    public ResponseEntity<BoletaConDetalleDTO> getCompleteBoleta(@PathVariable Long id) {
        BoletaConDetalleDTO dto = boletaService.getCompleteBoleta(id);
        return ResponseEntity.ok(dto);
    }
}