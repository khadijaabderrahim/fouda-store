package com.sema4.foudastore.services;

import com.sema4.foudastore.entities.Product;
import com.sema4.foudastore.exceptions.ElementNotFoundException;
import com.sema4.foudastore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent())
            return product.get();
        throw new ElementNotFoundException("product "+id+" not found");

    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
