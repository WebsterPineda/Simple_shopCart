package com.webster.controller;

import com.webster.models.Producto;
import com.webster.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProducto(@PathVariable Long id) {
        return productoRepository.findById(id).orElse(null);
    }
}
