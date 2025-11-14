package com.utn.productos_api.exception;

public class StockInsuficienteException extends RuntimeException {

    public StockInsuficienteException() {
        super("El stock no puede ser negativo.");
    }
}