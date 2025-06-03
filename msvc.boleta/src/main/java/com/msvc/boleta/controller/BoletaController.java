package com.msvc.boleta.controller;

import com.msvc.boleta.client.ClienteService;
import com.msvc.boleta.models.entities.Boleta;
import com.msvc.boleta.service.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boletas") // Puedes ajustar este path base si es necesario
public class BoletaController {

    // 👇 Inyectamos el ClienteService
    private final ClienteService clienteService;

    @Autowired
    private BoletaService boletaService;

    public BoletaController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Boleta>> getAll() {
        return ResponseEntity.ok(boletaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boleta> getById(@PathVariable Long id) {
        return ResponseEntity.ok(boletaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Boleta> create(@RequestBody Boleta boleta) {
        return ResponseEntity.ok(boletaService.save(boleta));
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


}