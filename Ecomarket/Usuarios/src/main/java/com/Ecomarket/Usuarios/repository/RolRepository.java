package com.Ecomarket.Usuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecomarket.Usuarios.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long>{
    Rol findByNombreRol(String nombreRol);
    List<Rol> findAllReclamaciones();
}