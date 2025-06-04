package com.msvc.detalleBoleta.repositories;

import com.msvc.detalleBoleta.models.entities.DetalleBoleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleBoletaRepository extends JpaRepository<DetalleBoleta, Long> {

    List<DetalleBoleta> findAllByIdBoleta(Long idBoleta);

}