package com.Ecomarket.Logistica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecomarket.Logistica.model.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, String> {

    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar proveedores por nombre o ubicación
    List<Proveedor> findByNombre(String nombre);
    List<Proveedor> findByUbicacion(String ubicacion);

    
} 
