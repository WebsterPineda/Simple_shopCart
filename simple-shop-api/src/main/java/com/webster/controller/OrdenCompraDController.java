package com.webster.controller;

import com.webster.models.OrdenCompraD;
import com.webster.repository.OrdenCompraDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/venta_detalle")
public class OrdenCompraDController {

    @Autowired
    private OrdenCompraDRepository ordenCompraDRepository;

    @GetMapping
    public List<OrdenCompraD> getAllCompras(){
        return ordenCompraDRepository.findAll();
    }

    @GetMapping("/{id}")
    public OrdenCompraD getArticuloVenta(@PathVariable Long id) {
        return ordenCompraDRepository.findById(id).orElse(null);
    }
}
