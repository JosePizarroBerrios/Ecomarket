package com.Ecomarket.Usuarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarjeta")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Tarjeta {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tarjeta;

    @Column(unique = true,length = 16,nullable = false)
    private String numero_Tarjeta;
    
    @Column(unique = false,length = 3, nullable = true)
    private String cvv;

    @Column(unique = false,length = 100,nullable = false)
    private String nom_titular;

    @Column(unique = false,nullable = false)
    private String fecha_vencimiento;

    @ManyToOne
    @JoinColumn(name = "idUsuario",nullable = false)
    private Usuario usuario;


}
