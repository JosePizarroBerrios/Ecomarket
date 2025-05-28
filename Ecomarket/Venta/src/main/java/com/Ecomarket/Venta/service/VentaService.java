package com.Ecomarket.Venta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecomarket.Venta.model.Cupon;
import com.Ecomarket.Venta.model.DetalleVenta;
import com.Ecomarket.Venta.model.Venta;
import com.Ecomarket.Venta.repository.CuponRepository;
import com.Ecomarket.Venta.repository.DetalleVentaRepository;
import com.Ecomarket.Venta.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private CuponRepository cuponRepository;

    public Venta crearVenta(Venta venta) {
        // Guardar la venta y sus detalles (si existen)
        Venta savedVenta = ventaRepository.save(venta);
        if (venta.getDetallesVenta() != null) {
            for (DetalleVenta detalle : venta.getDetallesVenta()) {
                detalle.setIdVenta(Long.valueOf(savedVenta.getIdVenta()));
                detalleVentaRepository.save(detalle);
            }
        }
        return savedVenta;
    }

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    public boolean agregarDetalleVenta(DetalleVenta detalleVenta) {
        // Verifica que la venta exista antes de agregar el detalle
        Venta venta = findById(detalleVenta.getIdVenta());
        if (venta != null) {
            detalleVentaRepository.save(detalleVenta);
            return true;
        }
        return false;
    }

    public Venta findById(Long idVenta) {
        Optional<Venta> ventaOpt = ventaRepository.findById(idVenta);
        return ventaOpt.orElse(null);
    }

    public boolean agregarDetalleAVenta(Long idVenta, DetalleVenta detalleVenta) {
        Venta venta = findById(idVenta);
        if (venta != null) {
            detalleVenta.setIdVenta(idVenta);
            detalleVentaRepository.save(detalleVenta);
            return true;
        }
        return false;
    }

    public boolean validarCupon(String codigoCupon) {
        Optional<Cupon> cuponOpt = cuponRepository.findByCodigo(codigoCupon);
        return cuponOpt.isPresent() && cuponOpt.get().getActivo();
    }

    public String generarFactura(Long ventaId) {
        // Placeholder: Implementar lógica real de generación de factura
        Venta venta = findById(ventaId);
        if (venta != null) {
            return "Factura generada para venta: " + ventaId;
        }
        return null;
    }
}
