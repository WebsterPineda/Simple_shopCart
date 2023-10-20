package com.webster.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductoDTO {
    private Long id;
    private String descripcion;
    private String unidadMedida;
    private BigDecimal precio;
}
