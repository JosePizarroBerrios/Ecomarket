package com.Ecomarket.Logistica.service;


import java.util.List;


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



    public Envio save(Envio envio) {
        return envioRepository.save(envio);
    }



    public Envio findById(Long id) {
        return envioRepository.findById(id).orElse(null);
    }

 


}
