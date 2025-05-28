package com.Ecomarket.Venta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecomarket.Venta.model.Cupon;

public interface CuponRepository extends JpaRepository<Cupon, String> {

    Optional<Cupon> findByCodigo(String codigoCupon);

}
