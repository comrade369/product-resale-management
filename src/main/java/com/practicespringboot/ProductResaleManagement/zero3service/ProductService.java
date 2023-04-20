package com.practicespringboot.ProductResaleManagement.zero3service;

import com.practicespringboot.ProductResaleManagement.payloads.ProductDto;
import com.practicespringboot.ProductResaleManagement.payloads.ProductSearchDto;
import com.practicespringboot.ProductResaleManagement.zero1entity.Product;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto, Long ownerId);

    List<ProductDto> getPostByOwner(Long ownerId);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long productId);

    void deleteProduct(Long productId);

    ProductDto updateProduct(ProductDto productDto, Long productId);


}
