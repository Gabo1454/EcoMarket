package com.msvc.venta.service;

import com.msvc.venta.exception.VentaException;
import com.msvc.venta.models.entities.Venta;
import com.msvc.venta.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta buscarVenta(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new VentaException("Venta con ID " + id + " no encontrada"));
    }

    @Override
    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public Venta actualizarVenta(Venta venta) {
        Venta ventaExistente = buscarVenta(venta.getIdVenta());
        ventaExistente.setFechaVenta(venta.getFechaVenta());
        ventaExistente.setTotal(venta.getTotal());
        ventaExistente.setIdCliente(venta.getIdCliente());
        ventaExistente.setIdBoleta(venta.getIdBoleta());
        return ventaRepository.save(ventaExistente);
    }

    @Override
    public void eliminarVenta(Long id) {
        Venta venta = buscarVenta(id);
        ventaRepository.delete(venta);
    }
}