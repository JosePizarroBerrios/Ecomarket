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
    private Long id_reclamo;

    @Column(unique = true,length = 100,nullable = false)
    private String asunto_reclamo;

    @Column(unique = true,length = 200,nullable = false)
    private String mensaje_reclamo;

    @Column (nullable = true)
    private String fechaReclamacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @Column(nullable = false)
    private String estado;

    public Reclamacion save(Reclamacion reclamacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }



}