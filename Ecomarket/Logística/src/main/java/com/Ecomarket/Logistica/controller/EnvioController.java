package com.Ecomarket.Logistica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ecomarket.Logistica.model.Envio;
import com.Ecomarket.Logistica.service.EnvioService;

@RestController
@RequestMapping("/api/v1/envios")

public class EnvioController {
        @Autowired
    private EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<Envio>> listar(){
        List<Envio> envios = envioService.listarEnvios();
        if (envios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(envios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> obtenerEnvio(@PathVariable Long id){
       return envioService.obtenerEnvio(id)
       .map(ResponseEntity::ok)
       .orElse(ResponseEntity.notFound().build());
        
    }
    @GetMapping("/{buscarEstado}")
    public List<Envio>buscarPorEstado(@RequestParam String estado){
        return envioService.buscarPorEstado(estado);
    }

    @GetMapping("/{buscarDestino}")
    public List<Envio>buscarPorDestino(@RequestParam String destino){
    return envioService.buscarPorDestino(destino);
    }

    @PostMapping Envio crearEnvio(@RequestBody Envio envio){
        return envioService.save(envio);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizarEnvio(@PathVariable Long id, @RequestBody Envio envio){
        try{
            Envio envi = envioService.findById(id);
            envi.setId(id);
            envi.setNom_usuario(envio.getNom_usuario());
            envi.setAp_usuario(envio.getAp_usuario());
            envi.setDestino(envio.getDestino());
            envi.setFechaEnvio(envio.getFechaEnvio());
            envi.setFechaEntregaEstimada(envio.getFechaEntregaEstimada());

            envioService.save(envi);
            return ResponseEntity.ok(envio);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEnvio(@PathVariable Long id){
        if(envioService.obtenerEnvio(id).isPresent()){
        envioService.delete(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}