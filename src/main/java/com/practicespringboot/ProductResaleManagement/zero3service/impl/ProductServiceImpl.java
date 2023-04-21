package com.practicespringboot.ProductResaleManagement.zero3service.impl;

import com.practicespringboot.ProductResaleManagement.exceptions.ResourceNotFoundException;
import com.practicespringboot.ProductResaleManagement.payloads.ProductDto;
import com.practicespringboot.ProductResaleManagement.payloads.ProductSearchDto;
import com.practicespringboot.ProductResaleManagement.payloads.ProductSpecification;
import com.practicespringboot.ProductResaleManagement.payloads.SearchCriteria;
import com.practicespringboot.ProductResaleManagement.zero1entity.Owner;
import com.practicespringboot.ProductResaleManagement.zero1entity.Product;
import com.practicespringboot.ProductResaleManagement.zero4repository.OwnerRepository;
import com.practicespringboot.ProductResaleManagement.zero4repository.ProductRepository;
import com.practicespringboot.ProductResaleManagement.zero3service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
        public ProductDto createProduct(ProductDto productDto, Long ownerId) {

        Owner owner = this.ownerRepository.findById(ownerId).orElseThrow(() ->
                new ResourceNotFoundException("Owner", "ownerId", ownerId));
        Product product = this.modelMapper.map(productDto, Product.class);
        product.setOwner(owner);
        product.setProductPurchaseDate(new Date());
        Product addProduct = this.productRepository.save(product);
        return this.modelMapper.map(addProduct, ProductDto.class);

    }

    @Override
    public List<ProductDto> getPostByOwner(Long ownerId) {
        Owner owner = this.ownerRepository.findById(ownerId).orElseThrow(() ->
                new ResourceNotFoundException("Owner", "ownerId", ownerId));
        List<Product> products = this.productRepository.findByOwner(owner);
        List<ProductDto> productDtos = products.stream().map((product) -> this.modelMapper.map(product, ProductDto.class)).toList();
        return productDtos;
    }

    @Override
    public List<ProductDto> getAllProducts(Integer pageNumber, Integer pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Product> pages = this.productRepository.findAll(page);
        List<Product> productList = pages.getContent();
        List<ProductDto> productDtoList = productList.stream().map((product) -> this.modelMapper.map(product, ProductDto.class)).toList();
        return productDtoList;
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = this.productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "productId", productId));
        return this.modelMapper.map(product, ProductDto.class);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = this.productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "productId", productId));
        this.productRepository.delete(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long productId) {
        Product product = this.productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "productId", productId));

        product.setProductModel(productDto.getProductModel());
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductModel(productDto.getProductModel());

        Product updatedProduct = this.productRepository.save(product);
        return this.modelMapper.map(updatedProduct, ProductDto.class);

    }
}
