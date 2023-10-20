package com.webster.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class SellDetailDTO {
    private Long id;
    private ProductoDTO product;
    private Integer qty;
    private BigDecimal unitPrice;
    private BigDecimal total;
}
