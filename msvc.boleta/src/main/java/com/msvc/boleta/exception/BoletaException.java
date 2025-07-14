package com.msvc.boleta.exception;

public class BoletaException extends RuntimeException {
    public BoletaException(String mensaje, Throwable cause) {
        super(mensaje);
    }
}
