package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponseDTO {
    @Schema(description = "Identificador único del producto generado por la BD", example = "1")
    private Long id;

    @Schema(description = "Nombre del producto", example = "Notebook Dell Inspiron")
    private String nombre;

    @Schema(description = "Descripción del producto", example = "Laptop con procesador Intel i5 y 16GB RAM")
    private String descripcion;

    @Schema(description = "Precio final del producto", example = "350.00")
    private Double precio;

    @Schema(description = "Stock disponible", example = "12")
    private Integer stock;

    @Schema(description = "Categoría del producto", example = "HOGAR")
    private Categoria categoria;
}
