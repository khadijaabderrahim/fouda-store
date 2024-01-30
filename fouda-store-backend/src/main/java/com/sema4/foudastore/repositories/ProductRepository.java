package com.sema4.foudastore.repositories;

import com.sema4.foudastore.entities.Product;
import com.sema4.foudastore.exceptions.ElementNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
