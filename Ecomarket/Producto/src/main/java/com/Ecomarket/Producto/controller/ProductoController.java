package com.Ecomarket.Producto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Ecomarket.Producto.model.Producto;
import com.Ecomarket.Producto.service.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    @Autowired ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listar(){
        List<Producto> productos = productoService.listaProductos();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<Producto>Guardar(@RequestBody Producto producto) {
        Producto productoNuevo = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscar (@RequestParam Long id) {
        try {
            Producto producto = productoService.FindById(id);
            return ResponseEntity.ok(producto);
        } catch (Exception e ){
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto){
        try {
            Producto pro =  productoService.FindById(id);
            pro.setId(id);
            pro.setNombreProducto(producto.getNombreProducto());
            pro.setCodigo(producto.getCodigo());
            pro.setDescripcionProducto(producto.getDescripcionProducto());
            pro.setPreciUnitario(producto.getPreciUnitario());
            pro.setStock(producto.getStock());
            pro.setCategoria(producto.getCategoria());

            productoService.save(pro);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            productoService.delete(id);
            return ResponseEntity.noContent().build();

        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    
    

}