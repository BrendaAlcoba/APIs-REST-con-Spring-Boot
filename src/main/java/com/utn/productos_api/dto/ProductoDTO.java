package com.utn.productos_api.dto;


import com.utn.productos_api.model.Categoria;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductoDTO {

    // Nombre: no nulo, no vacío, longitud entre 3 y 100 caracteres [cite: 284]
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    // Descripción: longitud máxima 500 caracteres [cite: 285]
    @Size(max = 500, message = "La descripción no debe superar los 500 caracteres")
    private String descripcion;

    // Precio: no nulo, valor mínimo 0.01 [cite: 286]
    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio debe ser mayor o igual a cero")
    @Positive(message = "El precio debe ser un valor positivo") // Asegura que sea > 0
    private Double precio;

    // Stock: no nulo, valor mínimo 0 [cite: 287]
    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    // Categoría: no nula [cite: 287]
    @NotNull(message = "La categoría es obligatoria")
    private Categoria categoria;
}