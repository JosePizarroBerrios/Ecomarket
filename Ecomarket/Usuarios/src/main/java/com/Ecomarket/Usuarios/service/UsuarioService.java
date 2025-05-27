package com.Ecomarket.Usuarios.service;



import java.time.LocalDate;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecomarket.Usuarios.model.Devolucion;
import com.Ecomarket.Usuarios.model.Reclamacion;

import com.Ecomarket.Usuarios.model.Usuario;
import com.Ecomarket.Usuarios.repository.DevolucionRepository;
import com.Ecomarket.Usuarios.repository.ReclamacionRepository;

import com.Ecomarket.Usuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private DevolucionRepository devolucionRepository;
    @Autowired
    private ReclamacionRepository reclamacionRepository;

    

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario autenticar(String email, String contrasena) {
        Usuario usuario = usuarioRepository.findByEmailUsuario(email)
            .orElseThrow(() -> new RuntimeException("Email no registrado"));

    if (!usuario.isActivo()) {
        throw new RuntimeException("La cuenta está desactivada");
    }
    
    if (!usuario.getContraseña_usuario().equals(contrasena)) {
        throw new RuntimeException("Contraseña incorrecta");
    }

    return usuario;

    }
    public Usuario registrarCliente(String nombre, String email, String contrasena) {
    if (usuarioRepository.existsByEmailUsuario(email)) {
        throw new RuntimeException("El email ya está en uso");
    }
    Usuario nuevo = new Usuario();
    nuevo.setNom_usuario(nombre);
    nuevo.setEmailUsuario(email);
    nuevo.setContraseña_usuario(contrasena); 
    nuevo.setActivo(true);

    return usuarioRepository.save(nuevo);
    }


    
    
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }






    public Devolucion registrarDevolucion(Long usuarioId, String motivo, Long productoId) {
        Usuario cliente = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Devolucion devolucion = new Devolucion();
        devolucion.setCliente(cliente);
        devolucion.setMotivo(motivo);
        devolucion.setProductoId(productoId); // solo se guarda el ID
        devolucion.setFechaDevolucion(LocalDate.now().toString());
        devolucion.setEstado("Solicitada");

        return devolucionRepository.save(devolucion);
    }
    public Reclamacion registrarReclamo(Long usuarioId, String asunto, String descripcion) {
        Usuario cliente = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Reclamacion reclamacion = new Reclamacion();
        reclamacion.setCliente(cliente);
        reclamacion.setAsunto(asunto);
        reclamacion.setMensaje(descripcion);
        reclamacion.setFechaReclamo(LocalDate.now().toString());
        reclamacion.setEstado("Pendiente");

        return reclamacionRepository.save(reclamacion);
    }
}




