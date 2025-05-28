package com.Ecomarket.Usuarios.model;


import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(unique = false,length = 25,nullable = false)
    private String nombreUsuario;

    @Column(unique = false,length = 25,nullable = false)
    private String apatUsuario; 

    @Column(unique = true,length = 50,nullable = false)
    private String emailUsuario;

    @Column(unique = false,length = 25,nullable = false)
    private String contraseñaUsuario;

    @Column(unique = false,length = 50,nullable = false) 
    private String dirUsuario;

    @Column(unique = false, nullable = false)
    private boolean activo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarjeta> tarjetas= new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idRol")
        )
    private Set<Rol> roles = new HashSet<>();

}