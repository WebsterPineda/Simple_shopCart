package com.webster.services;

import com.webster.dto.ProductoDTO;
import com.webster.enums.Responses;
import com.webster.exceptions.BaseNotFoundException;
import com.webster.interfaces.IProductoService;
import com.webster.models.Producto;
import com.webster.repository.ProductoRepository;
import com.webster.response.BaseResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private ProductoRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public BaseResponse<List<ProductoDTO>> getAllProducts() {
        List<Producto> productos = repository.findAll();

        List<ProductoDTO> products = productos.stream()
                .map(producto -> mapper.map(producto, ProductoDTO.class))
                .collect(Collectors.toList());

        BaseResponse<List<ProductoDTO>> rsp = new BaseResponse<>();

        Responses answer = products.isEmpty() ? Responses.NOT_FOUND : Responses.SUCCESS;

        rsp.setResponse(answer);
        rsp.setData(products);

        return rsp;
    }

    @Override
    public BaseResponse<ProductoDTO> getProductById(Long productId) {
        Producto producto = getProductoEntity(productId);

        BaseResponse<ProductoDTO> rsp = new BaseResponse<>();

        rsp.setResponse(Responses.SUCCESS);
        rsp.setData(mapper.map(producto, ProductoDTO.class));

        return rsp;
    }

    @Override
    public BaseResponse<ProductoDTO> createProduct(ProductoDTO product) {
        BaseResponse<ProductoDTO> rsp = new BaseResponse<>();
        try {
            Producto tmp = mapper.map(product, Producto.class);
            tmp = repository.save(tmp);

            rsp.setResponse(Responses.SUCCESS);
            rsp.setData(mapper.map(tmp, ProductoDTO.class));
        } catch (Exception e) {
            e.printStackTrace();
            rsp.setResponse(Responses.GURU_BUSY);
            rsp.setData(null);
        }

        return rsp;
    }

    @Override
    public BaseResponse<ProductoDTO> updateProduct(Long productId, ProductoDTO product) {
        getProductoEntity(productId);

        product.setId(productId);

        Producto updated = mapper.map(product, Producto.class);
        updated = repository.save(updated);

        BaseResponse<ProductoDTO> rsp = new BaseResponse<>();

        rsp.setResponse(Responses.SUCCESS);
        rsp.setData(mapper.map(updated, ProductoDTO.class));

        return rsp;
    }

    @Override
    public void deleteProduct(Long productId) {
        Producto producto = getProductoEntity(productId);

        repository.delete(producto);
    }

    public Producto getProductoEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new BaseNotFoundException("El producto consultado no existe");
        });
    }
}
