package com.Ecomarket.Usuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ecomarket.Usuarios.model.SolicitudSoporte;
import com.Ecomarket.Usuarios.service.SoporteService;

@RestController
@RequestMapping("/api/soporte")

public class SoporteController {

    @Autowired
    private  SoporteService soporteService;

    public SoporteController(SoporteService soporteService) {
        this.soporteService = soporteService;
    }

    @PostMapping
    public ResponseEntity<SolicitudSoporte> nuevoSoporte(@RequestParam Long clienteId,@RequestBody SolicitudSoporte solicitudSoporte){
        try{
        SolicitudSoporte nuevoSoporte = soporteService.enviarSoporte(clienteId, solicitudSoporte.getAsunto(), solicitudSoporte.getMensaje());
        return ResponseEntity.ok(nuevoSoporte);
    }catch(Exception e){
        return ResponseEntity.badRequest().build();
    }
    }
}
