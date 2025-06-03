package com.msvc.inventario.controller;

import com.msvc.inventario.models.entities.Inventario;
import com.msvc.inventario.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> getAll() {
        return ResponseEntity.ok(inventarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> getById(@PathVariable Long id) {
        return ResponseEntity.ok(inventarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Inventario> create(@Valid @RequestBody Inventario inventario) {
        return ResponseEntity.ok(inventarioService.save(inventario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> update(@PathVariable Long id, @Valid @RequestBody Inventario inventario) {
        Inventario existente = inventarioService.findById(id);
        existente.setIdProducto(inventario.getIdProducto());
        existente.setStock(inventario.getStock());
        existente.setFechaInventario(inventario.getFechaInventario());
        return ResponseEntity.ok(inventarioService.save(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}