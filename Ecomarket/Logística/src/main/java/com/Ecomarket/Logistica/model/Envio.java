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
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(unique = true, length = 25, nullable = false)
private String origen;

@Column(unique = true,length = 25,nullable = false)
private String nom_usuario;

@Column(unique = false,length = 25,nullable = false)
private String ap_usuario; 

@Column(unique = false,length = 25,nullable=true)
private String dir_usuario;

@Column(unique = true,length = 50,nullable = false)
private String email_Usuario;

@Column(unique = false, length = 20, nullable = false)
private String estado; 

@Column(unique = false, length = 20, nullable = false)
private String destino;

@Column (nullable = true)   
private String fechaEnvio;

@Column (nullable = true)
private String fechaEntregaEstimada;
}