package com.practicespringboot.ProductResaleManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products_table")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String productType;

    @Column(nullable = false)
    private String productModel;

    @Column(nullable = false)
    private Integer productPrice;

    @Column(nullable = false)
    private Date productPurchaseDate;

    @ManyToOne
    @JoinColumn(name = "productOwner")
    private Owner owner;
}
