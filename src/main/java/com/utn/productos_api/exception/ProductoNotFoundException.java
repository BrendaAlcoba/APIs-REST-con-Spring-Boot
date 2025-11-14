package com.utn.productos_api.exception;

// Hereda de RuntimeException (excepción no verificada)
public class ProductoNotFoundException extends RuntimeException {

    public ProductoNotFoundException(Long id) {
        super("El producto con ID " + id + " no fue encontrado.");
    }
}
