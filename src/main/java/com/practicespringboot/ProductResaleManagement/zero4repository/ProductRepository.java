package com.practicespringboot.ProductResaleManagement.zero4repository;

import com.practicespringboot.ProductResaleManagement.zero1entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

   Product findByProductName(String productName);

    Product findByProductNameAndProductType(String productName, String productType);



}
