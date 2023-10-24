package com.webster.controller;

import com.webster.dto.ClienteDTO;
import com.webster.response.BaseResponse;
import com.webster.services.ClientService;
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
@RequestMapping(value = "/api/cliente", produces = "application/json")
public class ClienteController {

    @Autowired
    private ClientService service;

    @GetMapping()
    public BaseResponse<List<ClienteDTO>> getClientes() {
        return service.getAllClients();
    }

    @GetMapping("/{id}")
    public BaseResponse<ClienteDTO> getCliente(@PathVariable Long id) {
        return service.getClienteById(id);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<BaseResponse<ClienteDTO>> createClient(@RequestBody ClienteDTO client) {
        BaseResponse<ClienteDTO> created = service.createCliente(client);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getData().getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public BaseResponse<ClienteDTO> updateClient(@PathVariable Long id, @RequestBody ClienteDTO client) {
        return service.updateCliente(id, client);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        service.deleteCliente(id);

        return ResponseEntity.noContent().build();
    }
}
