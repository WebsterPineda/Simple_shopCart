package com.webster.services;

import com.webster.dto.SellDTO;
import com.webster.enums.Responses;
import com.webster.exceptions.BaseNotFoundException;
import com.webster.interfaces.IOrdenCompraEService;
import com.webster.models.Cliente;
import com.webster.models.OrdenCompraE;
import com.webster.repository.OrdenCompraERepository;
import com.webster.response.BaseResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenCompraEService implements IOrdenCompraEService {

    @Autowired
    private OrdenCompraERepository ordenCompraERepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper mapper;

    @Override
    public BaseResponse<List<SellDTO>> getAllVentas() {
        List<OrdenCompraE> sells = ordenCompraERepository.findAll();

        if (sells.isEmpty()) {
            throw new BaseNotFoundException("No se ha registrado ninguna venta aun");
        }

        List<SellDTO> sellList = sells.stream()
                .map(sell -> mapper.map(sell, SellDTO.class))
                .collect(Collectors.toList());

        BaseResponse<List<SellDTO>> rsp = new BaseResponse<>();

        rsp.setResponse(Responses.SUCCESS);
        rsp.setData(sellList);

        return rsp;
    }

    @Override
    public BaseResponse<SellDTO> getVenta(Long id) {
        OrdenCompraE sell = getOrdenCompraEntity(id);

        BaseResponse<SellDTO> rsp = new BaseResponse<>();
        rsp.setResponse(Responses.SUCCESS);
        rsp.setData(mapper.map(sell, SellDTO.class));

        return rsp;
    }

    @Override
    public BaseResponse<SellDTO> createSell(SellDTO sell) {
        Cliente client = clientService.getClienteEntityById(sell.getClientId());
        OrdenCompraE saved = mapper.map(sell, OrdenCompraE.class);
        saved.setClient(client);

        saved = ordenCompraERepository.save(saved);

        BaseResponse<SellDTO> rsp = new BaseResponse<>();
        rsp.setResponse(Responses.SUCCESS);
        rsp.setData(mapper.map(saved, SellDTO.class));

        return rsp;
    }

    @Override
    public BaseResponse<SellDTO> updateSell(Long id, SellDTO newSell) {
        getOrdenCompraEntity(id);
        Cliente client = clientService.getClienteEntityById(newSell.getClientId());

        OrdenCompraE updated = mapper.map(newSell, OrdenCompraE.class);

        updated.setId(id);
        updated.setClient(client);

        updated = ordenCompraERepository.save(updated);

        BaseResponse<SellDTO> rsp = new BaseResponse<>();
        rsp.setResponse(Responses.SUCCESS);
        rsp.setData(mapper.map(updated, SellDTO.class));

        return rsp;
    }

    @Override
    public void deleteSell(Long id) {
        OrdenCompraE sell = getOrdenCompraEntity(id);
        ordenCompraERepository.delete(sell);
    }

    public OrdenCompraE getOrdenCompraEntity(Long id) {
        return ordenCompraERepository.findById(id).orElseThrow(() -> {
            throw new BaseNotFoundException("La venta solicitada no existe");
        });
    }
}
