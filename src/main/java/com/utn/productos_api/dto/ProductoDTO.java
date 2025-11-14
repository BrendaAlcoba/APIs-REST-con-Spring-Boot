package com.utn.productos_api.dto;


import com.utn.productos_api.model.Categoria;
import jakarta.validation.constraints.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class ProductoDTO {

    // Nombre: no nulo, no vacío, longitud entre 3 y 100 caracteres [cite: 284]
    @Schema(description = "Nombre del producto", example = "Smartphone Galaxy S22") // Nuevo
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    // Descripción: longitud máxima 500 caracteres
    @Schema(description = "Descripción detallada del producto", example = "Equipo móvil de alta gama con cámara 108MP") // Nuevo
    @Size(max = 500, message = "La descripción no debe superar los 500 caracteres")
    private String descripcion;

    // Precio: no nulo, valor mínimo 0.01
    @Schema(description = "Precio del producto en pesos", example = "199.99", required = true)
    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio mínimo debe ser 0.01")
    private Double precio;

    // Stock: no nulo, valor mínimo 0 [cite: 287]
    @Schema(description = "Cantidad disponible en stock", example = "10", required = true)
    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    // Categoría: no nula [cite: 287]
    @Schema(description = "Categoría del producto", example = "ELECTRONICA", required = true)
    @NotNull(message = "La categoría es obligatoria")
    private Categoria categoria;
}