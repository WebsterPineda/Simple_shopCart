package com.webster.controller;

import com.webster.dto.ProductoDTO;
import com.webster.response.BaseResponse;
import com.webster.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/producto", produces = "application/json")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public BaseResponse<List<ProductoDTO>> getProductos() {
        return service.getAllProducts();
    }

    @GetMapping(value = "/{id}")
    public BaseResponse<ProductoDTO> getProducto(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<BaseResponse<ProductoDTO>> createProduct(@RequestBody ProductoDTO product) {
        BaseResponse<ProductoDTO> rsp = service.createProduct(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(rsp.getData().getId())
                .toUri();

        return ResponseEntity.created(location).body(rsp);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public BaseResponse<ProductoDTO> updateProduct(@PathVariable Long id,
                                                   @RequestBody ProductoDTO product) {
        return service.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}
