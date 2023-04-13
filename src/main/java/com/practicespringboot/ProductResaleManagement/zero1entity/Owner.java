package com.practicespringboot.ProductResaleManagement.zero1entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ownerInfo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;

    @Column(nullable = false)
    private String ownerName;

    @Column(nullable = false, unique = true)
    private String ownerEmail;

    @Column(nullable = false, unique = true)
    private Long ownerPhone;

    @Column(nullable = false)
    private String ownerAddress;

    @Column(nullable = false)
    private String ownerPinCode;

    @OneToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "productOwner")
    private Product product;
}
