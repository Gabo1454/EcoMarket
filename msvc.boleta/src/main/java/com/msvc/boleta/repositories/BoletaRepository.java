package com.msvc.boleta.repositories;

import com.msvc.boleta.models.entities.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Long> {

    List<Boleta> findByIdClientePojo(Long idClientePojo);

}
