package com.msvc.detalleBoleta.repositories;

import com.msvc.detalleBoleta.models.entities.DetalleBoleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleBoletaRepository extends JpaRepository<DetalleBoleta, Long> {
}