package com.webster.services;

import com.webster.dto.ClienteDTO;
import com.webster.enums.Responses;
import com.webster.exceptions.BaseNotFoundException;
import com.webster.interfaces.IClienteService;
import com.webster.models.Cliente;
import com.webster.repository.ClienteRepository;
import com.webster.response.BaseResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService implements IClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public BaseResponse<List<ClienteDTO>> getAllClients() {
        List<Cliente> clientList = repository.findAll();

        List<ClienteDTO> clients = clientList.stream()
                .map(cliente -> mapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());

        BaseResponse<List<ClienteDTO>> rsp = new BaseResponse<>();

        Responses answer = clients.isEmpty() ? Responses.NOT_FOUND : Responses.SUCCESS;

        rsp.setResponse(answer);
        rsp.setData(clients);

        return rsp;
    }

    @Override
    public BaseResponse<ClienteDTO> getClienteById(Long clientId) {
        Cliente client = getClienteEntityById(clientId);

        BaseResponse<ClienteDTO> rsp = new BaseResponse<>();

        rsp.setResponse(Responses.SUCCESS);
        rsp.setData(mapper.map(client, ClienteDTO.class));

        return rsp;
    }

    @Override
    public BaseResponse<ClienteDTO> createCliente(ClienteDTO client) {
        Cliente created = mapper.map(client, Cliente.class);

        created = repository.save(created);

        BaseResponse<ClienteDTO> rsp = new BaseResponse<>();

        rsp.setResponse(Responses.SUCCESS);
        rsp.setData(mapper.map(created, ClienteDTO.class));

        return rsp;
    }

    @Override
    public BaseResponse<ClienteDTO> updateCliente(Long clientId, ClienteDTO client) {
        getClienteEntityById(clientId);

        client.setId(clientId);

        Cliente newClient = mapper.map(client, Cliente.class);

        newClient = repository.save(newClient);

        BaseResponse<ClienteDTO> rsp = new BaseResponse<>();

        rsp.setResponse(Responses.SUCCESS);
        rsp.setData(mapper.map(newClient, ClienteDTO.class));

        return rsp;
    }

    @Override
    public void deleteCliente(Long clientId) {
        Cliente client = getClienteEntityById(clientId);

        repository.delete(client);
    }

    public Cliente getClienteEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new BaseNotFoundException("El cliente consultado no existe");
        });
    }
}
