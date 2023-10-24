package com.webster.interfaces;

import com.webster.dto.SellDTO;
import com.webster.response.BaseResponse;

import java.util.List;

public interface IOrdenCompraEService {
    BaseResponse<List<SellDTO>> getAllVentas();

    BaseResponse<SellDTO> getVenta(Long id);

    BaseResponse<SellDTO> createSell(SellDTO sell);

    BaseResponse<SellDTO> updateSell(Long id, SellDTO sell);

    void deleteSell(Long id);
}
