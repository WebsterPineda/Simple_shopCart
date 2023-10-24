package com.webster.interfaces;

import com.webster.dto.ClienteDTO;
import com.webster.response.BaseResponse;

import java.util.List;

public interface IClienteService {
    BaseResponse<List<ClienteDTO>> getAllClients();

    BaseResponse<ClienteDTO> getClienteById(Long clientId);

    BaseResponse<ClienteDTO> createCliente(ClienteDTO client);

    BaseResponse<ClienteDTO> updateCliente(Long clientId, ClienteDTO client);

    void deleteCliente(Long clientId);
}
