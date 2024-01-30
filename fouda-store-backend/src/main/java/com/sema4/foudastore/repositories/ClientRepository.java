package com.sema4.foudastore.repositories;

import com.sema4.foudastore.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
