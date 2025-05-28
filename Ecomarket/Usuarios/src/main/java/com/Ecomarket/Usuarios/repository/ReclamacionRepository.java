package com.Ecomarket.Usuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Ecomarket.Usuarios.model.Reclamacion;
import com.Ecomarket.Usuarios.model.Usuario;

@Repository
public interface ReclamacionRepository extends JpaRepository<Reclamacion, Long> {
    List<Reclamacion> findByUsuario(Usuario usuario);
    List<Reclamacion> findAll();
}