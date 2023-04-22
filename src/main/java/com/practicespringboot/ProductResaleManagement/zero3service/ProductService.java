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
    List<ProductDto> searchProductName(String keyword);
    List<ProductDto> searchProductModel(String keyword);

    List<ProductDto> searchProductType(String keyword);

    List<ProductDto> searchProductPrice(Integer price);

    List<ProductDto> searchProductNameByQuery(String keyword);
    ProductResponse getPageInfo(Integer pageNumber, Integer pageSize);
}
