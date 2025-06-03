package com.msvc.boleta.service;

import com.msvc.boleta.exception.BoletaException;
import com.msvc.boleta.models.entities.Boleta;
import com.msvc.boleta.repositories.BoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoletaServiceImpl implements BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    @Override
    public List<Boleta> findAll() {
        return boletaRepository.findAll();
    }

    @Override
    public Boleta findById(Long id) {
        return boletaRepository.findById(id)
                .orElseThrow(() -> new BoletaException("La boleta con ID " + id + " no existe en la base de datos"));
    }

    @Override
    public Boleta save(Boleta boleta) {
        return boletaRepository.save(boleta);
    }

    @Override
    public void deleteById(Long id) {
        boletaRepository.deleteById(id);
    }


}