package com.msvc.detalleBoleta.service;

import com.msvc.detalleBoleta.models.entities.DetalleBoleta;
import com.msvc.detalleBoleta.repositories.DetalleBoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleBoletaServiceImpl implements DetalleBoletaService {

    @Autowired
    private DetalleBoletaRepository repository;

    @Override
    public List<DetalleBoleta> findAll() {
        return repository.findAll();
    }

    @Override
    public DetalleBoleta findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("DetalleBoleta con ID " + id + " no encontrado"));
    }

    @Override
    public DetalleBoleta save(DetalleBoleta detalleBoleta) {
        return repository.save(detalleBoleta);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}