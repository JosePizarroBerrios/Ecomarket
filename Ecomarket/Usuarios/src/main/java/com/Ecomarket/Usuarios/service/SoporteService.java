package com.Ecomarket.Usuarios.service;



import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecomarket.Usuarios.model.SolicitudSoporte;
import com.Ecomarket.Usuarios.model.Usuario;
import com.Ecomarket.Usuarios.repository.SolicitudSoporteRepository;
import com.Ecomarket.Usuarios.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SoporteService {
    @Autowired
    private SolicitudSoporteRepository solicitudSoporteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

 public SolicitudSoporte enviarSoporte(Long clienteId, String asunto, String mensaje) {
        Usuario cliente = usuarioRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        SolicitudSoporte s = new SolicitudSoporte();
        s.setAsunto(asunto);
        s.setMensaje(mensaje);
        s.setFechaSolicSoporte(LocalDate.now().toString());
        s.setCliente(cliente);
        s.setEstado("Enviado");

        return solicitudSoporteRepository.save(s);
    }
}