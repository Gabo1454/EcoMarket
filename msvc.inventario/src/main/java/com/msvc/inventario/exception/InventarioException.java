package com.msvc.inventario.exception;

public class InventarioException extends RuntimeException {
    public InventarioException(String mensaje) {
        super(mensaje);
    }
}