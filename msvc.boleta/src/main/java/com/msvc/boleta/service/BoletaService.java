package com.msvc.boleta.service;

import com.msvc.boleta.dto.BoletaConDetalleDTO;
import com.msvc.boleta.models.entities.Boleta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoletaService {

    List<Boleta> findAll();
    Boleta findById(Long id);
    Boleta save(Boleta boleta);
    void deleteById(Long id);
    //Se podria crear un DTO para solo solicitar la informacion importante y no toda

    BoletaConDetalleDTO getCompleteBoleta(Long id);

}
