package com.Ecomarket.Logistica.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "envio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long idEnvio;

    @Column(nullable = false, length = 100)
    private String dirOrigen;

    @Column(nullable = false, length = 100)
    private String dirDestino;
    
    @Column(nullable = false)
    private String estado;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column(nullable = false)
    private Long idUsuario; 

    
}
