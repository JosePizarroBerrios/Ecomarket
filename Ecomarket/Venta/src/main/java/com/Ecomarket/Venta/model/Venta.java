package com.Ecomarket.Venta.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
    
@Entity
@Table(name = "ventas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idVenta;


    @Column(nullable = false, length = 50)
    private Long clienteId;

    @Column(nullable = false, length = 50)
    private String fechaVenta;

    @Column(nullable = false)
    private int totalVenta;

    @Column(nullable = false, length = 50)
    private String facturaId;

    @OneToMany(mappedBy = "venta",
               cascade = jakarta.persistence.CascadeType.ALL,
               orphanRemoval = true)
    private List<DetalleVenta> detallesVenta;
}
