package com.Ecomarket.Usuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecomarket.Usuarios.model.Devolucion;

@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion,Long> {
    List<Devolucion> findByClienteId(Long idUsuario);
    
}