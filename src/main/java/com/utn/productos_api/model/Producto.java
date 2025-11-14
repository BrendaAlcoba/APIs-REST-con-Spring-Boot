package com.utn.productos_api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres") // APLICAR ESTE CAMBIO
    private String nombre;

    @Size(max = 500, message = "La descripción no debe superar los 500 caracteres") // APLICAR ESTE CAMBIO
    private String descripcion;

    @Positive(message = "El precio debe ser mayor a 0")
    private double precio;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;

    @NotNull(message = "La categoría es obligatoria")
    @Enumerated(EnumType.STRING)   // @Enumerated
    private Categoria categoria;
}
