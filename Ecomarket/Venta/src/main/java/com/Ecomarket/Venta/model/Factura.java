package com.Ecomarket.Venta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "factura")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String numFactura;

    @Column(nullable = false, length = 50)
    private String clienteId;

    @Column(nullable = false, length = 50)
    private String fechaEmision;

    @Column(nullable = false, length = 50)
    private int subtotal;
    
    @Column(nullable = false, length = 50)
    private int total;




  
}



