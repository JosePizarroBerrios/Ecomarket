package com.Ecomarket.Logistica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ecomarket.Logistica.model.Envio;
import com.Ecomarket.Logistica.model.Proveedor;
import com.Ecomarket.Logistica.model.Tienda;
import com.Ecomarket.Logistica.service.EnvioService;
import com.Ecomarket.Logistica.service.ProveedorService;
import com.Ecomarket.Logistica.service.TiendaService;

@RestController
@RequestMapping("/envios")

public class EnvioController {
    
    @Autowired
    private EnvioService envioService;
    @Autowired
    private TiendaService tiendaService;
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/tiendas")
    public List<Tienda> listarTiendas() {
        return tiendaService.listarTiendas();
    }

    @PostMapping("/tiendas")
    public Tienda agregarTienda(@RequestBody Tienda tienda) {
        return tiendaService.save(tienda);
    }

    @PutMapping("/tiendas/{id}")
    public ResponseEntity<Tienda> actualizarTienda(@PathVariable Long id, @RequestBody Tienda tienda) {
        Tienda tiendaExistente = tiendaService.findById(id);
        if (tiendaExistente != null) {
            return ResponseEntity.ok(tiendaService.save(tienda));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/provedores")
    public ResponseEntity<List<Proveedor>> listarProveedores() {
        List<Proveedor> proveedores = proveedorService.listarProveedores();
        if (proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proveedores);
    }

    @PostMapping("/provedores")
    public Proveedor agregarProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.save(proveedor);
    }

    @PutMapping("/provedores/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable String    id, @RequestBody Proveedor proveedor) {
        Proveedor proveedorExistente = proveedorService.findById(id);
        if (proveedorExistente != null) {
            return ResponseEntity.ok(proveedorService.save(proveedor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/envios")
    public List<Envio> listarEnvios() {
        return envioService.listarEnvios();
    }
    @PostMapping("/envios")
    public Envio agregarEnvio(@RequestBody Envio envio) {
        return envioService.save(envio);
    }
    @PutMapping("/envios/{id}")
    public ResponseEntity<Envio> actualizarEstadoEnvio(@PathVariable Long id, @RequestParam String estadoEnvio) {
        Envio envioExistente = envioService.findById(id);
        if (envioExistente != null) {
            envioExistente.setEstado(estadoEnvio);
            return ResponseEntity.ok(envioService.save(envioExistente));
        } else {
            return ResponseEntity.notFound().build();}
    }

    @GetMapping("/envios/estado/{id}")
    public ResponseEntity<String> verEstadoEnvio(@PathVariable Long id) {
        Envio envio = envioService.findById(id);
        if (envio != null) {
            return ResponseEntity.ok(envio.getEstado());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


    

    

