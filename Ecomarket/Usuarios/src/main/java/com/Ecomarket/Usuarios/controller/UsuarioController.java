package com.Ecomarket.Usuarios.controller;

import org.springframework.web.bind.annotation.RestController;

import com.Ecomarket.Usuarios.model.LoginRequest;
import com.Ecomarket.Usuarios.model.Reclamacion;
import com.Ecomarket.Usuarios.model.Rol;
import com.Ecomarket.Usuarios.model.SolicitudSoporte;
import com.Ecomarket.Usuarios.model.Usuario;
import com.Ecomarket.Usuarios.service.UsuarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/usuarios")


public class UsuarioController {
    @Autowired 
    UsuarioService usuarioService;

    @PostMapping                          // Endpoint para guardar un nuevo usuario
    public ResponseEntity<String> guardarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado correctamente: "+ nuevoUsuario );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")                // Endpoint para iniciar sesión
    public ResponseEntity<Usuario> iniciarSesion(@RequestParam String email, @RequestParam String password) {
        LoginRequest usuario = new LoginRequest(email, password);// Crear un objeto LoginRequest con los parámetros recibidos
        
        if (usuario.getEmailUsuario() == null || usuario.getPassword() == null) { // Validar que los campos no sean nulos
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            Usuario usuarioEncontrado = usuarioService.login(usuario);//valida las credenciales del usuario
            if (usuarioEncontrado != null) {
                return ResponseEntity.ok(usuarioEncontrado);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/perfil/{id}") // Endpoint para obtener el perfil del usuario
    public ResponseEntity<Usuario> obtenerPerfil(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.findByIdUsuario(id); // Método que busca el usuario por ID
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/perfil/{id}")//endpoint para actualizar el perfil del usuario
    public void actualizarPerfil(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        try {
            Usuario usuario = usuarioService.findByIdUsuario(id); // Método que busca el usuario por ID
            if (usuario != null) {
                usuarioService.save(usuarioActualizado); // Método que actualiza el perfil del usuario
            } else {
                throw new Exception("Usuario no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el perfil del usuario: " + e.getMessage());
        }
    }
    
    @PostMapping("/soporte")
    public void crearSolicitudSoporte(@RequestBody SolicitudSoporte solicitudSoporte) {
        try {
            usuarioService.saveSolicitudSoporte(solicitudSoporte);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la solicitud de soporte: " + e.getMessage());
        }
    }

    @GetMapping("/admin/listar")    // Endpoint para listar todos los usuarios
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.findAll(); // Método que obtiene todos los usuarios
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/admin/{id}") // endopoint para actualizar un usuario como administrador
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        try {
            Usuario usuario = usuarioService.findByIdUsuario(id); // Método que busca el usuario por ID
            if (usuario != null) {
                usuarioActualizado.setIdUsuario(id); // Asegurarse de que el ID del usuario actualizado sea correcto
                Usuario usuarioGuardado = usuarioService.save(usuarioActualizado); // Método que actualiza el usuario
                return ResponseEntity.ok(usuarioGuardado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @PutMapping("/admin/desactivar/{id}") // Endpoint para desactivar o activar un usuario
    public ResponseEntity<String> desactivarActivarUsuario(@PathVariable Long id) {
        try {
            usuarioService.desactivarUsuario(id); // Método que desactiva o activa el usuario
            return ResponseEntity.ok("Usuario actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    @PutMapping("/admin/activar/{id}")
    public ResponseEntity<String> activarUsuario(@PathVariable Long id) {
        try {
            usuarioService.activarUsuario(id); // Método que activa el usuario
            return ResponseEntity.ok("Usuario activado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al activar el usuario: " + e.getMessage());
        }
    }

    @PutMapping("/admin/rol/{id}")
    public ResponseEntity<String> asignarRol(@PathVariable Long id, @RequestParam String rol) {
        try {
            Long rolId = usuarioService.findRolByNombre(rol); // Método que busca el rol por ID
            if (rolId != null) {
                Usuario usuario = usuarioService.findByIdUsuario(id); // Método que busca el usuario por ID
                if (usuario == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
                }else{
                    usuario.getRoles().add(new Rol(rolId, rol)); // Asignar el rol al usuario
                    usuarioService.save(usuario); // Método que guarda el usuario actualizado
                    
                }
                return ResponseEntity.ok("Rol asignado correctamente: " + rol);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al asignar el rol: " + e.getMessage());
        }
    }



    @PostMapping("/reclamacion")
    public ResponseEntity<String> crearReclamacion(@RequestBody Reclamacion reclamacion) {
        try {
            usuarioService.saveReclamacion(reclamacion); // Método que guarda la reclamación
            return ResponseEntity.status(HttpStatus.CREATED).body("Reclamación creada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la reclamación: " + e.getMessage());
        }
    }
    

    


    
    
}
