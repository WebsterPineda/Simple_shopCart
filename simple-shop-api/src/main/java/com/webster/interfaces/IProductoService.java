package com.webster.interfaces;

import com.webster.dto.ProductoDTO;
import com.webster.response.BaseResponse;

import java.util.List;

public interface IProductoService {
    BaseResponse<List<ProductoDTO>> getAllProducts();

    BaseResponse<ProductoDTO> getProductById(Long productId);

    BaseResponse<ProductoDTO> createProduct(ProductoDTO product);

    BaseResponse<ProductoDTO> updateProduct(Long productId, ProductoDTO product);

    void deleteProduct(Long productId);
}
