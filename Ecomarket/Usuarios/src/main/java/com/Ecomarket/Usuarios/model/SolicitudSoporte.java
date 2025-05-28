package com.Ecomarket.Usuarios.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "solicitudes_soporte")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudSoporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;

    @Column(unique = true,length = 200,nullable = false)
    private String asuntoSolicitud;
    
    @Column(unique = true,length = 200,nullable = false)
    private String detalleSolicitud;

    @Column(nullable = true)
    private String fechaSolicitud;

    @Column(nullable = true)
    private String estado; 

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

}