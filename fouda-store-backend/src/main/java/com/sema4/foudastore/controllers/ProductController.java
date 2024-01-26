package com.sema4.foudastore.controllers;

import com.sema4.foudastore.entities.Product;
import com.sema4.foudastore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController extends ExceptionHandling {

    @Autowired
    private ProductService productService;

    // all products
    @GetMapping("/")
    ResponseEntity<List<Product>> findAll() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(productService.findAll());
    }

    //product detail by id
    @GetMapping("/{id}")
    ResponseEntity<Product> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    // add product
    @PostMapping("/")
    ResponseEntity save(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //delete product by id
    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
