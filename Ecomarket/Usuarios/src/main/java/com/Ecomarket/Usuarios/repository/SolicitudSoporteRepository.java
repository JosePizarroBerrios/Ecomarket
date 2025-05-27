package com.Ecomarket.Usuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecomarket.Usuarios.model.SolicitudSoporte;

@Repository
public interface SolicitudSoporteRepository extends JpaRepository<SolicitudSoporte, Long> {
    List<SolicitudSoporte> findByClienteId(Long clienteId);
}
