package com.sema4.foudastore.repositories;

import com.sema4.foudastore.entities.Product;
import com.sema4.foudastore.exceptions.ElementNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements InMemoryRepositoty<Product,Long>{

    private List<Product> products = new ArrayList<>();

    private Long getNextId() {
        return products.stream().mapToLong(Product::getId).max().orElse(0L)+1;
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
       return products.stream().filter(p -> id.equals(p.getId())).findAny();
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            product.setId(getNextId());
            products.add(product);
        } else {
            Optional<Product> productInRepo = products.stream().filter(p -> product.getId().equals(p.getId())).findAny();
            productInRepo.ifPresent(value -> products.remove(value));
            products.add(product);
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<Product> product = products.stream().filter(p ->id.equals(p.getId())).findAny();
        if(product.isPresent()) {
            products.remove(product.get());
        } else {
            throw new ElementNotFoundException("product with id "+id+" not found");
        }
    }
}
