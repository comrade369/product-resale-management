package com.practicespringboot.ProductResaleManagement.zero4repository;

import com.practicespringboot.ProductResaleManagement.payloads.ProductDto;
import com.practicespringboot.ProductResaleManagement.zero1entity.Owner;
import com.practicespringboot.ProductResaleManagement.zero1entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findByOwner(Owner owner);

}
