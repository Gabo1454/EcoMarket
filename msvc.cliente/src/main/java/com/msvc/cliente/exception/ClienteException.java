package com.msvc.cliente.exception;

public class ClienteException extends RuntimeException {
    public ClienteException(String mensaje) {
        super(mensaje);
    }
}