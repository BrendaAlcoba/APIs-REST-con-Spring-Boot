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
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private Categoria categoria;
}
