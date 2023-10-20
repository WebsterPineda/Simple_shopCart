package com.webster.controller;

import com.webster.dto.SellDTO;
import com.webster.dto.SellDetailDTO;
import com.webster.response.BaseResponse;
import com.webster.services.OrdenCompraDService;
import com.webster.services.OrdenCompraEService;
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
@RequestMapping(value = "/api/venta", produces = "application/json")
public class OrdenCompraEController {
    @Autowired
    private OrdenCompraEService service;

    @Autowired
    private OrdenCompraDService detailService;

    @GetMapping
    public BaseResponse<List<SellDTO>> getVentas() {
        return service.getAllVentas();
    }

    @GetMapping("/{id}")
    public BaseResponse<SellDTO> getVenta(@PathVariable Long id) {
        return service.getVenta(id);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<BaseResponse<SellDTO>> createOrdenCompra(@RequestBody SellDTO sell) {
        BaseResponse<SellDTO> created = service.createSell(sell);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getData().getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public BaseResponse<SellDTO> updateSell(@PathVariable Long id, @RequestBody SellDTO sell) {
        return service.updateSell(id, sell);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteSell(@PathVariable Long id) {
        service.deleteSell(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints for details
    @GetMapping("/{sellId}/detail")
    public BaseResponse<List<SellDetailDTO>> getDetallesVentaPorVentaId(@PathVariable Long sellId) {
        return detailService.getAllDetallesVenta(sellId);
    }

    @PostMapping(value = "/{sellId}/detail", consumes = "application/json")
    public ResponseEntity<BaseResponse<SellDetailDTO>> creadeDetalleVenta(@PathVariable Long sellId,
                                                                          @RequestBody SellDetailDTO sellDetail) {
        BaseResponse<SellDetailDTO> created = detailService.createSellDetail(sellId, sellDetail);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{sellId}/detail/{detailId}")
                .buildAndExpand(sellId,
                        created.getData().getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{sellId}/detail/{detailId}")
    public BaseResponse<SellDetailDTO> updateDetailSell(@PathVariable Long sellId, @PathVariable Long detailId, @RequestBody SellDetailDTO detail) {
        return detailService.updateSellDetail(sellId, detailId, detail);
    }

    @DeleteMapping("/{sellId}/detail/{detailId}")
    public ResponseEntity<Void> deleteDetail(@PathVariable Long sellId, @PathVariable Long detailId) {
        detailService.deleteSellDetail(sellId, detailId);
        return ResponseEntity.noContent().build();
    }
}
