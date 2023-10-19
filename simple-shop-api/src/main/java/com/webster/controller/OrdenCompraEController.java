package com.webster.controller;

import com.webster.models.OrdenCompraE;
import com.webster.repository.OrdenCompraERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/venta")
public class OrdenCompraEController {
    @Autowired
    private OrdenCompraERepository ordenCompraERepository;

    @GetMapping
    public List<OrdenCompraE> getVentas(){
        return ordenCompraERepository.findAll();
    }

    @GetMapping("/{id}")
    public OrdenCompraE getDetalleVenta(@PathVariable Long id){
        return ordenCompraERepository.findById(id).orElse(new OrdenCompraE());
    }
}
