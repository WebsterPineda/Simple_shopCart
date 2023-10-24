package com.webster.services;

import com.webster.dto.ProductoDTO;
import com.webster.dto.SellDTO;
import com.webster.dto.SellDetailDTO;
import com.webster.enums.Responses;
import com.webster.exceptions.BaseNotFoundException;
import com.webster.interfaces.IOrdenCompraDService;
import com.webster.models.OrdenCompraD;
import com.webster.models.OrdenCompraE;
import com.webster.models.Producto;
import com.webster.repository.OrdenCompraDRepository;
import com.webster.response.BaseResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenCompraDService implements IOrdenCompraDService {

    @Autowired
    private OrdenCompraDRepository ordenCompraDRepository;

    @Autowired
    private OrdenCompraEService headerService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ModelMapper mapper;

    @Override
    public BaseResponse<List<SellDetailDTO>> getAllDetallesVenta(Long sellId) {
        OrdenCompraE sell = headerService.getOrdenCompraEntity(sellId);

        List<OrdenCompraD> sells = sell.getDetalle();

        List<SellDetailDTO> details = sells.stream()
                .map(sellDetail -> mapper.map(sellDetail, SellDetailDTO.class))
                .collect(Collectors.toList());

        BaseResponse<List<SellDetailDTO>> rsp = new BaseResponse<>();

        Responses answer = details.isEmpty() ? Responses.NOT_FOUND : Responses.SUCCESS;

        rsp.setResponse(answer);
        rsp.setData(details);

        return rsp;
    }

    @Override
    public BaseResponse<SellDetailDTO> createSellDetail(Long sellId, SellDetailDTO sellDetail) {
        OrdenCompraE venta = headerService.getOrdenCompraEntity(sellId);

        ProductoDTO productoDTO = mapper.map(productoService.getProductoEntity(sellDetail.getProduct().getId()), ProductoDTO.class);
        sellDetail.setProduct(productoDTO);

        OrdenCompraD saved = mapper.map(sellDetail, OrdenCompraD.class);
        saved.setHeaderOrder(venta);

        saved = ordenCompraDRepository.save(saved);

        updateTotalSell(sellId);

        BaseResponse<SellDetailDTO> rsp = new BaseResponse<>();
        rsp.setResponse(Responses.SUCCESS);
        rsp.setData(mapper.map(saved, SellDetailDTO.class));

        return rsp;
    }

    @Override
    public BaseResponse<SellDetailDTO> updateSellDetail(Long sellId, Long sellDetailId,
                                                        SellDetailDTO newSell) {
        headerService.getOrdenCompraEntity(sellId);
        OrdenCompraD current = getDetalleEntity(sellDetailId);

        if (!current.getHeaderOrder().getId().equals(sellId)) {
            throw new BaseNotFoundException("El detalle no se ha encontrado en la venta especificada");
        }

        Producto producto = productoService.getProductoEntity(newSell.getProduct().getId());

        OrdenCompraD updated = mapper.map(newSell, OrdenCompraD.class);
        updated.setHeaderOrder(current.getHeaderOrder());
        updated.setProduct(producto);
        updated.setId(sellDetailId);

        updated = ordenCompraDRepository.save(updated);

        updateTotalSell(sellId);

        BaseResponse<SellDetailDTO> rsp = new BaseResponse<>();
        rsp.setResponse(Responses.SUCCESS);
        rsp.setData(mapper.map(updated, SellDetailDTO.class));

        return rsp;
    }

    @Override
    public void deleteSellDetail(Long sellId, Long sellDetailId) {
        headerService.getOrdenCompraEntity(sellId);
        OrdenCompraD detalle = getDetalleEntity(sellDetailId);

        if (!detalle.getHeaderOrder().getId().equals(sellId)) {
            throw new BaseNotFoundException("El detalle no se ha encontrado en la venta especificada");
        }

        ordenCompraDRepository.delete(detalle);
    }

    private OrdenCompraD getDetalleEntity(Long ventaDetalleId) {
        return ordenCompraDRepository.findById(ventaDetalleId).orElseThrow(() -> {
            throw new BaseNotFoundException("El detalle de venta que se esta consultando " +
                    "no existe");
        });
    }

    private void updateTotalSell(Long sellId) {
        OrdenCompraE sell = headerService.getOrdenCompraEntity(sellId);

        BigDecimal total = sell.getDetalle()
                .stream()
                .map(OrdenCompraD::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        sell.setTotal(total);

        headerService.updateSell(sellId, mapper.map(sell, SellDTO.class));
    }
}
