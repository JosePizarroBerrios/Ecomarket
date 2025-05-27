package com.Ecomarket.Usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Ecomarket.Usuarios.model.Rol;
import com.Ecomarket.Usuarios.model.Usuario;
import com.Ecomarket.Usuarios.repository.RolRepository;
import com.Ecomarket.Usuarios.service.AdminService;


@RestController
@RequestMapping("/api/admin/usuarios")

public class AdminController {

    @Autowired
    private AdminService adminService;
    
    
    @Autowired
    private RolRepository rolRepository;

    @GetMapping
    public List<Usuario>findall(){
        return adminService.findAll();
    }
    public boolean esAdmin(Usuario usuario) {
    for (Rol rol : usuario.getRoles()) {
        if ("ADMIN".equals(rol.getNombre_rol())) {
            return true;
        }
    }
    return false;
}
    @PostMapping("/api/admin/crearUsuario")
    public ResponseEntity<String> crearUsuarioConRol(@RequestParam String nombre,@RequestParam String email,@RequestParam String contrasena,@RequestParam Long id_rol) {

    try {
        // Crear el usuario
        Usuario nuevo = adminService.crearUsuario(nombre, email, contrasena);

        // Buscar y asignar el rol
        Rol rol = rolRepository.findById(id_rol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        nuevo.getRoles().add(rol);

        adminService.save(nuevo);
        return ResponseEntity.ok("Usuario creado con éxito");

    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Error al crear usuario: " + e.getMessage());
    }
}
    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        try{
            Usuario usu = adminService.findById(id);
            usu.setId(id);
            usu.setNom_usuario(usu.getNom_usuario());
            usu.setAp_usuario(usu.getAp_usuario());
            usu.setEmailUsuario(usu.getEmailUsuario());
            usu.setDir_usuario(usu.getDir_usuario());
            usu.setMetodoPago(usu.getMetodoPago());
            
            adminService.save(usuario);
            return ResponseEntity.ok(usuario);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        adminService.desactivarActivarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        adminService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}

