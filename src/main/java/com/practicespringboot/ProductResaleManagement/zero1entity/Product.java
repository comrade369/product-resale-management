package com.practicespringboot.ProductResaleManagement.zero1entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String productType;

    @Column(nullable = false)
    private String productModel;

    @OneToOne
    @JoinColumn(name = "productOwner", referencedColumnName = "ownerId")
    private Owner productOwner;

    @Column(nullable = false)
    private Integer productPrice;

    @Column(nullable = false)
    private LocalDate productPurchaseDate;
}
