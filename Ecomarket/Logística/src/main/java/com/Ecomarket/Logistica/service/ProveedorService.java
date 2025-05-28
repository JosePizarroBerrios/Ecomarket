package com.Ecomarket.Logistica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.Ecomarket.Logistica.model.Proveedor;
import com.Ecomarket.Logistica.repository.ProveedorRepository;

public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;
    
    public List<Proveedor> listarProveedores() {
        return proveedorRepository.findAll();
    }
        

    public Proveedor save(Proveedor proveedor){
        return proveedorRepository.save(proveedor);
    }

    public Proveedor findById(String id) {
        return proveedorRepository.findById(id).orElse(null);
    }

}
