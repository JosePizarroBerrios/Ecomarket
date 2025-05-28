package com.Ecomarket.Logistica.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tienda")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long idTienda;

    @Column(unique = true, nullable = false, length = 100)
    private String direccion;

    @Column(nullable = false, length = 50)
    private String horarioApertura;

    @Column(nullable = false, length = 50)
    private String horarioCierre;

    @Column(nullable = false, length = 50)
    private String estado;

    @ElementCollection
    private List<String> personalAsignado; // IDs de empleados

}
