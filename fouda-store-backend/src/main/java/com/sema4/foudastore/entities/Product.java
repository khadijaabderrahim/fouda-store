package com.sema4.foudastore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    //id, label, description, prix

    private Long id;

    private String label;

    private String description;

    private Double prix;

}
