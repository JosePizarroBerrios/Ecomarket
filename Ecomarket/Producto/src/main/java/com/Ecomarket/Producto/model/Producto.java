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
    Long idProducto;     

    @Column(unique = true, length = 100, nullable = false)
    String nombreProducto;   

    @Column(length = 500, nullable = false)
    String descripcionProducto;   

    @Column(length = 50, nullable = false)
    String categoriaProducto; 

    @Column(length = 50, nullable = false)
    String marcaProducto;                    
    
    @Column(nullable = false)
    double precioUnitarioProducto;   

    @Column(nullable = false)
    int stockProducto;                       
    
    @Column(nullable = false)
    boolean activo;

         

}
