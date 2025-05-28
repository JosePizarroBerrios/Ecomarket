package com.Ecomarket.Logistica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.Ecomarket.Logistica.model.Tienda;
import com.Ecomarket.Logistica.repository.TiendaRepository;

public class TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    public List<Tienda> listarTiendas() {
        return tiendaRepository.findAll();
    }

    public Tienda save(Tienda tienda) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    public Tienda findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

}
