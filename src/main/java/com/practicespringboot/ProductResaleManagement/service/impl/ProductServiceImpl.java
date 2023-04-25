package com.practicespringboot.ProductResaleManagement.service.impl;

import com.practicespringboot.ProductResaleManagement.entity.Owner;
import com.practicespringboot.ProductResaleManagement.entity.Product;
import com.practicespringboot.ProductResaleManagement.exceptions.ResourceNotFoundException;
import com.practicespringboot.ProductResaleManagement.payloads.*;
import com.practicespringboot.ProductResaleManagement.repository.OwnerRepository;
import com.practicespringboot.ProductResaleManagement.repository.ProductRepository;
import com.practicespringboot.ProductResaleManagement.service.ProductService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ProductRepository productRepository;

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

    @Override
    public List<ProductDto> productSearch(ProductSearchDto productSearchDto) {
        List<Product> results = new ArrayList<>();
        List<ProductSpecification> allSpecs = new ArrayList<>();
        if (productSearchDto.getProductName() != null && !productSearchDto.getProductName().trim().isEmpty()) {
            ProductSpecification productNameSpec = new ProductSpecification(new SearchCriteria("productName", ":", productSearchDto.getProductName()));
            allSpecs.add(productNameSpec);
        }

        if (productSearchDto.getProductType() != null && !productSearchDto.getProductType().trim().isEmpty()) {
            ProductSpecification productTypeSpec = new ProductSpecification(new SearchCriteria("productType", ":", productSearchDto.getProductType()));
            allSpecs.add(productTypeSpec);
        }

        if (productSearchDto.getProductModel() != null && !productSearchDto.getProductModel().trim().isEmpty()) {
            ProductSpecification productModelSpec = new ProductSpecification(new SearchCriteria("productModel", ":", productSearchDto.getProductModel()));
            allSpecs.add(productModelSpec);
        }

        if (productSearchDto.getProductPrice() != null) {
            ProductSpecification productPriceSec = new ProductSpecification(new SearchCriteria("productPrice", ":", productSearchDto.getProductPrice()));
            allSpecs.add(productPriceSec);
        }

        if (productSearchDto.getProductOwner() != null) {
            ProductSpecification productOwnerSpec = new ProductSpecification(new SearchCriteria("productOwner.ownerName", ":", productSearchDto.getProductOwner()));
            allSpecs.add(productOwnerSpec);
        }

        if (!allSpecs.isEmpty()) {
            Specification<Product> specifications = Specification.where(allSpecs.get(0));
            for (ProductSpecification spec : allSpecs) {
                specifications = specifications.and(spec);
            }

            results = productRepository.findAll(specifications);
        }
        else {
            results = productRepository.findAll();
        }
        List<ProductDto> productDtoList = new ArrayList<>();
        for (int i = 0; i < results.size(); i += 1) {
            productDtoList.add(this.modelMapper.map(results.get(i), ProductDto.class));
        }
        return productDtoList;
    }
}
