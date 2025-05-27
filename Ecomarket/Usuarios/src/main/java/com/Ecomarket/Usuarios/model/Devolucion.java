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
    private Long id;

    @Column(unique = true,length = 200,nullable = false)
    private String motivo;
    
    private Long productoId;

    @Column (nullable = true)
    private String fechaDevolucion;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;

    private String estado; // Ej: "Pendiente", "Procesada"

}