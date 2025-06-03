package com.msvc.venta.service;

import com.msvc.venta.models.entities.Venta;

import java.util.List;

public interface VentaService {
    List<Venta> listarVentas();
    Venta buscarVenta(Long id);
    Venta guardarVenta(Venta venta);
    Venta actualizarVenta(Venta venta);
    void eliminarVenta(Long id);
}
