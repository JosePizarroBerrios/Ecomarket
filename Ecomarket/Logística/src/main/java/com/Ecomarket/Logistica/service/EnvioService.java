package com.Ecomarket.Logistica.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecomarket.Logistica.model.Envio;
import com.Ecomarket.Logistica.repository.EnvioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;


    public List<Envio> listarEnvios() {
        return envioRepository.findAll();
    }

    public Optional<Envio> obtenerEnvio(Long id) {
        return envioRepository.findById(id);
    }

    public List<Envio> buscarPorEstado(String estado) {
        return envioRepository.findByEstado(estado);
    }

    public List<Envio> buscarPorDestino(String destino) {
        return envioRepository.findByDestino(destino);

    }
      public Envio findById(Long id) {
        return envioRepository.findById(id).orElse(null);
    }

    public Envio save(Envio envio) {
        // Cuando crees o actualices un Envio, usa:
        envio.setFechaEnvio(LocalDate.now().toString());
        envio.setFechaEntregaEstimada(LocalDate.now().plusDays(3).toString());
        return envioRepository.save(envio);
    }

    public void delete(Long id) {
        envioRepository.deleteById(id);
    }
}
