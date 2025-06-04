package com.msvc.boleta.client;

import com.msvc.boleta.dto.DetalleBoletaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "detalle-client", url = "${detalle.service.url}")
public interface DetalleBoletaClient {

    @GetMapping("/api/v1/detalleBoletas/boleta/{id}")
    List<DetalleBoletaDTO> findByBoletaId(@PathVariable("id") Long idBoleta);


}


