package com.practicespringboot.ProductResaleManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.practicespringboot.ProductResaleManagement.entity.Owner;
import com.practicespringboot.ProductResaleManagement.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findByOwner(Owner owner);
    List<Product> findByProductNameContaining(String keyword);

    List<Product> findByProductTypeContaining(String keyword);

    List<Product> findByProductModelContaining(String keyword);

    List<Product> findByProductPriceLessThan(Integer price);

    @Query("select p from Product p where p.productName like :key")
    List<Product> searchByProductName(@Param("key") String keyword);

}
