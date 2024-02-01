package com.sema4.foudastore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "product")
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "prix", nullable = false)
    private Double prix;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "image", nullable = false)
    private String image;

    public Product(String label, String description, Double prix, Integer rating, String image) {
        this.label = label;
        this.description = description;
        this.prix = prix;
        this.rating = rating;
        this.image = image;
    }
}
