package com.sema4.foudastore.repositories;

import com.sema4.foudastore.entities.Client;
import com.sema4.foudastore.exceptions.ElementNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository implements InMemoryRepositoty<Client, Long> {

    private List<Client> clients = new ArrayList<>();

    private Long getNextId() {
        return clients.stream().mapToLong(Client::getId).max().orElse(0L) + 1;
    }

    public List<Client> findAll() {
        return clients;
    }

    public Optional<Client> findById(Long id) {
        return clients.stream().filter(c -> id.equals(c.getId())).findAny();
    }

    public void save(Client client) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (client.getId() == null) {
            client.setId(getNextId());
            clients.add(client);
        } else {
            Optional<Client> clientInRepo = clients.stream().filter(c -> client.getId().equals(c.getId())).findAny();
            clientInRepo.ifPresent(value -> clients.remove(value));
            clients.add(client);
        }
    }

    public void deleteById(Long id) {
        Optional<Client> client = clients.stream().filter(c -> id.equals(c.getId())).findAny();
        if (client.isPresent()) {
            clients.remove(client.get());
        }
        else {
            throw new ElementNotFoundException("client " + id + " not found");
        }
    }


}
