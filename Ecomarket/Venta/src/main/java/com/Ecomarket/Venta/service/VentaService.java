package com.Ecomarket.Venta.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.Ecomarket.Venta.model.Cupon;
import com.Ecomarket.Venta.model.DetalleVenta;
import com.Ecomarket.Venta.model.Venta;
import com.Ecomarket.Venta.repository.CuponRepository;
import com.Ecomarket.Venta.repository.VentaRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class VentaService {
    
    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private CuponRepository cuponRepository;

    @Autowired
    private JavaMailSender mailSender;

    public List<Venta> listarVentas(){
        return ventaRepository.findAll();
    }

    public Venta registrarVenta(Venta venta , String codigoCupon){
        venta.setFechaVenta(LocalDate.now().toString());

        BigDecimal total =venta.getDetalle().stream()
        .map(d->d.getPrecioUnitario().multiply(BigDecimal.valueOf(d.getCantidad())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
        
   Optional<Cupon> cuponOpt = Optional.ofNullable(codigoCupon)
        .map(String::trim)
        .flatMap(codigo -> cuponRepository.findById(codigo));

    if (cuponOpt.isPresent() && Boolean.TRUE.equals(cuponOpt.get().getActivo())) {
    BigDecimal descuento = cuponOpt.get().getDescuento();
    total = total.subtract(total.multiply(descuento));
    }

    venta.setTotal(total);
    Venta listarVentas = ventaRepository.save(venta);

    enviarFacturaPorCorreo(listarVentas);
    return listarVentas;

    }
    private void enviarFacturaPorCorreo(Venta venta){
        try{
        StringBuilder cuerpo =new StringBuilder();
        cuerpo.append("Factura electronica\n\n");
        cuerpo.append("Cliente:").append(venta.getNom_usuario()).append("\n");
        cuerpo.append("fecha:").append(venta.getFechaVenta()).append("\n");
        cuerpo.append("Total:$").append(venta.getTotal()).append("\n\n");
        cuerpo.append("Datalle:\n");
        for (DetalleVenta d: venta.getDetalle()){
            cuerpo.append(" - ").append(d.getNombreProducto())
            .append(" x ").append(d.getCantidad())
            .append(" = $").append(d.getPrecioUnitario().multiply(BigDecimal.valueOf(d.getCantidad())))
            .append("\n");
        }cuerpo.append("</ul>");

            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
            helper.setTo(venta.getEmail());
            helper.setSubject("Factura electrónica - Venta " + venta.getId());
            helper.setText(cuerpo.toString(), true);

            mailSender.send(mensaje);

        } catch (MessagingException e) {
            System.err.println("Error al enviar correo: " + e.getMessage());
    }


    
}
}
