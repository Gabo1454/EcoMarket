package com.msvc.cliente.exceptions;

public class ClienteException extends RuntimeException {
    public ClienteException(String mensaje) {
        super(mensaje);
    }
}