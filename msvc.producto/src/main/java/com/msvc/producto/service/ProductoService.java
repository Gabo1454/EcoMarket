package com.msvc.producto.service;

import com.msvc.producto.models.entities.Producto;
import com.msvc.producto.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(Long id) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        return optionalProducto.orElse(null); // Puedes manejar la excepción según convenga
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto update(Long id, Producto producto) {
        Producto existingProducto = findById(id);
        if (existingProducto == null) {
            return null; // O lanza excepción
        }
        existingProducto.setNombre(producto.getNombre());
        existingProducto.setPrecio(producto.getPrecio());
        existingProducto.setDescripcion(producto.getDescripcion());
        existingProducto.setStock(producto.getStock());
        return productoRepository.save(existingProducto);
    }

    public boolean delete(Long id) {
        Producto producto = findById(id);
        if (producto == null) {
            return false;
        }
        productoRepository.delete(producto);
        return true;
    }
}
