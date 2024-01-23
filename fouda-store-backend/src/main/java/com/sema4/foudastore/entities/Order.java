package com.sema4.foudastore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    // id, client,liste des produits,date,status(new, expédié livré, annulé)

    private Long id;

    //@JsonIgnore
    private Client client;

    private List<Product> productList;

    private LocalDateTime orderDate;

    private Status status;
}
