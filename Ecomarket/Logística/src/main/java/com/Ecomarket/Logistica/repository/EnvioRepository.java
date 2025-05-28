package com.Ecomarket.Logistica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecomarket.Logistica.model.Envio;

public interface EnvioRepository extends JpaRepository<Envio, Long> {

    List<Envio> findByEstado(String estado);
    List<Envio> findByDestino(String destino);
}