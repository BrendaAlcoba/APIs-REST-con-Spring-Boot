package com.utn.productos_api.model;


import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
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

    private String nombre;

    private String descripcion;

    private double precio;

    private int stock;


    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}