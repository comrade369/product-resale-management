package com.practicespringboot.ProductResaleManagement.zero3service.impl;

import com.practicespringboot.ProductResaleManagement.exceptions.ResourceNotFoundException;
import com.practicespringboot.ProductResaleManagement.payloads.*;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

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
    public List<ProductDto> getPages(Integer pageNumber, Integer pageSize) {
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
    public List<ProductDto> getAllProducts() {
        List<Product> productList = this.productRepository.findAll();
        List<ProductDto> productDtoList = productList.stream().map((product) ->
                this.modelMapper.map(product, ProductDto.class)).toList();
        return productDtoList;
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = this.productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "productId", productId));
        this.productRepository.delete(product);
    }

    @Override
    public ProductResponse sortingProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = null;
        if (sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        }
        else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> productPage = this.productRepository.findAll(pageable);
        List<Product> productList = productPage.getContent();
        List<ProductDto> productDtoList = productList.stream().map((product) ->
                this.modelMapper.map(product, ProductDto.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setLastPage(productPage.isLast());
        productResponse.setTotalPages(productPage.getTotalPages());
        productResponse.setProducts(productDtoList);
        productResponse.setPageSize(productPage.getSize());
        productResponse.setPageNumber(productPage.getNumber());
        productResponse.setTotalElements((int) (productPage.getTotalElements()));
        return  productResponse;

    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long productId) {
        Product product = this.productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "productId", productId));

        product.setProductModel(productDto.getProductModel());
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductType(productDto.getProductType());

        Product updatedProduct = this.productRepository.save(product);
        return this.modelMapper.map(updatedProduct, ProductDto.class);

    }

    @Override
    public List<ProductDto> searchProductName(String keyword) {
        List<Product> productList = this.productRepository.findByProductNameContaining(keyword);
        List<ProductDto> productDtoList = productList.stream().map((product) ->
                this.modelMapper.map(product, ProductDto.class)).toList();
        return productDtoList;
    }

    @Override
    public List<ProductDto> searchProductModel(String keyword) {
        List<Product> productList = this.productRepository.findByProductModelContaining(keyword);
        List<ProductDto> productDtoList = productList.stream().map((product) ->
                this.modelMapper.map(product, ProductDto.class)).toList();
        return productDtoList;

    }

    @Override
    public List<ProductDto> searchProductType(String keyword) {
        List<Product> productList = this.productRepository.findByProductTypeContaining(keyword);
        List<ProductDto> productDtoList = productList.stream().map((product) ->
                this.modelMapper.map(product, ProductDto.class)).toList();
        return productDtoList;
    }

    @Override
    public List<ProductDto> searchProductPrice(Integer price) {
        List<Product> productList = this.productRepository.findByProductPriceLessThan(price);
        List<ProductDto> productDtoList = productList.stream().map((product) ->
                this.modelMapper.map(product, ProductDto.class)).toList();
        return productDtoList;

    }

    @Override
    public List<ProductDto> searchProductNameByQuery(String keyword) {
        List<Product> productList = this.productRepository.searchByProductName("%" + keyword + "%");
        List<ProductDto> productDtoList = productList.stream().map((product) -> this.modelMapper.map(product, ProductDto.class)).toList();
        return productDtoList;
    }


    @Override
    public ProductResponse getPageInfo(Integer pageNumber, Integer pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPage = this.productRepository.findAll(page);
        List<Product> productList = productPage.getContent();
        List<ProductDto> productDtoList = productList.stream().map((product) ->
                this.modelMapper.map(product, ProductDto.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(productDtoList);
        productResponse.setPageNumber(productPage.getNumber());
        productResponse.setPageSize(productPage.getSize());
        productResponse.setTotalPages(productPage.getTotalPages());
        productResponse.setTotalElements((int) (productPage.getTotalElements()));
        productResponse.setLastPage(productPage.isLast());

        return productResponse;
    }
}
