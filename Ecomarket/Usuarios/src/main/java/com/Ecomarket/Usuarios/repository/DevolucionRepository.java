package com.Ecomarket.Usuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecomarket.Usuarios.model.Devolucion;

@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion,Long> {
    // Si el campo en Usuario es 'id':
    List<Devolucion> findByClienteId(Long id);
    // Si el campo en Usuario es 'usuarioId':
    // List<Devolucion> findByClienteUsuarioId(Long usuarioId);
}