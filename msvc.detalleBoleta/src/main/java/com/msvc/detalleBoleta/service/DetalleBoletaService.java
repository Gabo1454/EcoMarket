package com.msvc.detalleBoleta.service;

import com.msvc.detalleBoleta.models.entities.DetalleBoleta;

import java.util.List;

public interface DetalleBoletaService {
    List<DetalleBoleta> findAll();

    DetalleBoleta findById(Long id);

    DetalleBoleta save(DetalleBoleta detalleBoleta);

    void deleteById(Long id);
}