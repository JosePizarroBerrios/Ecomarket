package com.Ecomarket.Usuarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reclamacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reclamacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,length = 100,nullable = false)
    private String asunto;

    @Column(unique = true,length = 200,nullable = false)
    private String mensaje;

    @Column (nullable = true)
    private String fechaReclamo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;

    private String estado;
}