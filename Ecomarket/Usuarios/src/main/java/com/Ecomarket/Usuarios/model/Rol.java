package com.Ecomarket.Usuarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rol")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rol;

    @Column
    private String nombre_rol; 

    @ManyToOne
    @JoinColumn(name = "id_usuario") 
    private Usuario usuario;
}