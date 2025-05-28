package com.Ecomarket.Usuarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "devolucion")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDevolucion;

    @Column(unique = true,length = 200,nullable = false)
    private String motivo;
    
    @Column(nullable = false)
    private Long idVenta;

    @Column (nullable = true)
    private String fechaDevolucion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario cliente;

    @Column(nullable = false)
    private String estado; // Ej: "Pendiente", "Procesada"

}