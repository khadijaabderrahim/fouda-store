package com.sema4.foudastore.services;

import com.sema4.foudastore.dto.ClientDetailResponse;
import com.sema4.foudastore.entities.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<Client> findAll();
    ClientDetailResponse findById(Long id);

    void save(Client client);

    void deleteById(Long id);


}
