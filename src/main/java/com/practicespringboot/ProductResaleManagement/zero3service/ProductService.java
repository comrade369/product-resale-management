package com.practicespringboot.ProductResaleManagement.zero3service;

import com.practicespringboot.ProductResaleManagement.dto.ProductSearchDto;
import com.practicespringboot.ProductResaleManagement.zero1entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    Product getProductById(Long productId);

    List<Product> getAllProducts();

    Product updateProduct(Product product);

    void deleteProduct(Long productId);

    List<Product> productSearch(ProductSearchDto productSearchRequest);
}
