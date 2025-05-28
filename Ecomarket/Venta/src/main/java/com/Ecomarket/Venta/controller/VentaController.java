package com.Ecomarket.Venta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ecomarket.Venta.model.DetalleVenta;
import com.Ecomarket.Venta.model.Venta;
import com.Ecomarket.Venta.service.VentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<Venta> listarVentas() {
        return ventaService.listarVentas();
    }
    @PostMapping("/nueva")
    public ResponseEntity<Venta> crearVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaService.crearVenta(venta);
        return ResponseEntity.ok(nuevaVenta);
    }

    @PostMapping("/factura")
    public ResponseEntity<String> generarFactura(@RequestParam Long ventaId) {
        String factura = ventaService.generarFactura(ventaId);
        if (factura != null) {
            return ResponseEntity.ok(factura);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/cupon")
    public ResponseEntity<String> validarCupon(@RequestBody String codigoCupon) {
        boolean valido = ventaService.validarCupon(codigoCupon);
        if (valido) {
            return ResponseEntity.ok("Cupón válido");
        } else {
            return ResponseEntity.badRequest().body("Cupón inválido");
        }
    }

    @PostMapping("/detalle")
    public ResponseEntity<String> agregarDetalleAVenta(@RequestParam Long idVenta, @RequestBody DetalleVenta detalleVenta) {
        Venta venta = ventaService.findById(idVenta);
        if (venta != null) {
            boolean agregado = ventaService.agregarDetalleVenta(detalleVenta);
            if (agregado) {
                return ResponseEntity.ok("Detalle agregado a la venta");
            } else {
                return ResponseEntity.badRequest().body("Error al agregar el detalle a la venta");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    
    
    }}
