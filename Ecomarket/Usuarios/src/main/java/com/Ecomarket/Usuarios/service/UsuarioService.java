package com.Ecomarket.Usuarios.service;

import java.util.List;

import javax.management.openmbean.InvalidKeyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecomarket.Usuarios.model.LoginRequest;
import com.Ecomarket.Usuarios.model.Reclamacion;
import com.Ecomarket.Usuarios.model.Rol;
import com.Ecomarket.Usuarios.model.SolicitudSoporte;
import com.Ecomarket.Usuarios.model.Usuario;
import com.Ecomarket.Usuarios.repository.*;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class UsuarioService {

    @Autowired
    private  UsuarioRepository usuarioRepository;
    @Autowired
    private SolicitudSoporteRepository solicitudSoporteRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private ReclamacionRepository reclamacionRepository;



    public Usuario login(LoginRequest Request) {
        for(Usuario usuario : usuarioRepository.findAll()) {
            if(usuario.getEmailUsuario().equals(Request.getEmailUsuario()) && usuario.getContraseñaUsuario().equals(Request.getPassword())) {
                return usuario;  // metodo que valida credenciales de usuario
            }else {
                throw new InvalidKeyException("Invalid email or password");
            }   
        }
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll(); // metodo que devuelve todos los usuarios
    }
    public Usuario findByIdUsuario(Long id_usuario) {
        for(Usuario usuario : usuarioRepository.findAll()) {
            if(usuario.getIdUsuario().equals(id_usuario)) {
                return usuario;  // metodo que busca un usuario por su id
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'findByIdUsuario'");
    }

    public SolicitudSoporte saveSolicitudSoporte(SolicitudSoporte solicitudSoporte) {
        solicitudSoporte.setEstado("Pendiente");
        solicitudSoporte.setFechaSolicitud(java.time.LocalDateTime.now().toString());
        
        return solicitudSoporteRepository.save(solicitudSoporte); // metodo que guarda una solicitud de soporte
    }

    public void desactivarUsuario(Long id_usuario) {
        Usuario usuario = findByIdUsuario(id_usuario);
        if (usuario != null) {
            usuario.setActivo(false); // metodo que desactiva un usuario
            usuarioRepository.save(usuario);
        } else {
            throw new UnsupportedOperationException("Unimplemented method 'desactivarUsuario'");
        }
    }
    public void activarUsuario(Long id_usuario) {
        Usuario usuario = findByIdUsuario(id_usuario);
        if (usuario != null) {
            usuario.setActivo(true); // metodo que activa un usuario
            usuarioRepository.save(usuario);
        } else {
            throw new UnsupportedOperationException("Unimplemented method 'activarUsuario'");
        }
    }
    public Long findRolByNombre(String nombreRol) {
    
        for(Rol rol : rolRepository.findAll()) {
            if(rol.getNombreRol().equals(nombreRol)) {
                return rol.getIdRol();  
            }
        }
        return null;  
    }

    public Reclamacion saveReclamacion(Reclamacion reclamacion) {
        reclamacion.setEstado("Pendiente");
        reclamacion.setFechaReclamacion(java.time.LocalDateTime.now().toString());
        return reclamacionRepository.save(reclamacion); // metodo que guarda una reclamacion
    }

    public Usuario save(Usuario usuario) {
        if (usuario.getIdUsuario() == null) {
            usuario.setActivo(true); // Si es un nuevo usuario, se activa por defecto
            usuarioRepository.save(usuario);
        }
        
        return usuario; // metodo que guarda un usuario
    }


    


}




