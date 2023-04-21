package com.practicespringboot.ProductResaleManagement.zero3service;

import com.practicespringboot.ProductResaleManagement.payloads.ProductResponse;
import com.practicespringboot.ProductResaleManagement.payloads.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto, Long ownerId);

    List<ProductDto> getPostByOwner(Long ownerId);

    List<ProductDto> getPages(Integer pageNumber, Integer pageSize);

    ProductDto getProductById(Long productId);
    List<ProductDto> getAllProducts();
    void deleteProduct(Long productId);
    ProductResponse sortingProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    ProductDto updateProduct(ProductDto productDto, Long productId);

    ProductResponse getPageInfo(Integer pageNumber, Integer pageSize);
}
