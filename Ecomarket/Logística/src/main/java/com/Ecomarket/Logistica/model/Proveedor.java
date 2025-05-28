package com.Ecomarket.Logistica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@Table(name = "proveedores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor {

    @Id
    private String rutProveedor;

    @Column(unique = false, nullable = false, length = 100)
    private String nombre;
    
    @Column(length = 100, nullable = false)
    private String direccion;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String telefono;

    @ElementCollection
    private List<String> productosSuministrados; // IDs de productos



}