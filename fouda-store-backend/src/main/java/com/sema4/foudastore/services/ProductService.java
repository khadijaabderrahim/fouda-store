package com.sema4.foudastore.services;

import com.sema4.foudastore.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();
    Product findById(Long id);

    void save(Product product);

    void deleteById(Long id);
}
