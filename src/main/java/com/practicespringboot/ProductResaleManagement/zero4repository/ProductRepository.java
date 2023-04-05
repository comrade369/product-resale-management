package com.practicespringboot.ProductResaleManagement.zero4repository;

import com.practicespringboot.ProductResaleManagement.zero1entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product, Long> {

}
