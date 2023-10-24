package com.webster.interfaces;

import com.webster.dto.SellDetailDTO;
import com.webster.response.BaseResponse;

import java.util.List;

public interface IOrdenCompraDService {
    BaseResponse<List<SellDetailDTO>> getAllDetallesVenta(Long sellId);

    BaseResponse<SellDetailDTO> createSellDetail(Long sellId, SellDetailDTO sellDetail);

    BaseResponse<SellDetailDTO> updateSellDetail(Long sellId, Long sellDetailId, SellDetailDTO sellDetail);

    void deleteSellDetail(Long sellId, Long sellDetailId);
}
