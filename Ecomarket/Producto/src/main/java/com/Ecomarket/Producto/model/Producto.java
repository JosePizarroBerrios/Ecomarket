package com.Ecomarket.Producto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable=false, unique = true)
    private String nombreProducto;

    @Column (nullable=false, unique=true)
    private int codigo;

    @Column (unique = true, length = 100, nullable=false)
    private String descripcionProducto;

    @Column (nullable = false)
    private int preciUnitario;

    @Column (nullable = false)
    private int stock;
    private String categoria;

}
