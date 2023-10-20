package com.webster.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class SellDTO {
    private Long id;
    private Long clientId;
    private LocalDateTime sellDate;
    private BigDecimal total;
    private List<SellDetailDTO> detalle;
}
