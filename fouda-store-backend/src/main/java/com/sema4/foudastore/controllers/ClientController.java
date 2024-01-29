package com.sema4.foudastore.controllers;

import com.sema4.foudastore.dto.ClientDetailResponse;
import com.sema4.foudastore.entities.Client;
import com.sema4.foudastore.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController extends ExceptionHandling {

    @Autowired
    private ClientService clientService;

    // all clients
    @GetMapping(value = "/",produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<Client>> findAll() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(clientService.findAll());
    }

    //client detail by id
    @GetMapping("/{id}")
    ResponseEntity<ClientDetailResponse> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    // add client
    @PostMapping("/")
    ResponseEntity save(@RequestBody Client client) {
        clientService.save(client);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    //delete client by id
    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
