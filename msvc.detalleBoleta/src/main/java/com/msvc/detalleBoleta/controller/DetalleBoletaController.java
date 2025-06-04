package com.msvc.detalleBoleta.controller;

import com.msvc.detalleBoleta.models.entities.DetalleBoleta;
import com.msvc.detalleBoleta.service.DetalleBoletaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalleBoleta")
public class  DetalleBoletaController {

    @Autowired
    private DetalleBoletaService service;

    @GetMapping
    public ResponseEntity<List<DetalleBoleta>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleBoleta> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<DetalleBoleta> create(@Valid @RequestBody DetalleBoleta detalle) {
        return ResponseEntity.ok(service.save(detalle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleBoleta> update(@PathVariable Long id, @Valid @RequestBody DetalleBoleta detalle) {
        DetalleBoleta existente = service.findById(id);
        existente.setIdBoleta(detalle.getIdBoleta());
        existente.setIdProducto(detalle.getIdProducto());
        existente.setCantidad(detalle.getCantidad());
        existente.setPrecioUnidad(detalle.getPrecioUnidad());
        existente.setSubTotal(detalle.getSubTotal());
        return ResponseEntity.ok(service.save(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}