package com.practicespringboot.ProductResaleManagement.controller;

import com.practicespringboot.ProductResaleManagement.config.AppConstants;
import com.practicespringboot.ProductResaleManagement.entity.Product;
import com.practicespringboot.ProductResaleManagement.payloads.*;
import com.practicespringboot.ProductResaleManagement.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;


    // build create product REST API
    @PostMapping("/owner/{ownerId}/products") // CREATE - POST
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto
            , @PathVariable Long ownerId) {
        ProductDto productDto1 = this.productService.createProduct(productDto, ownerId);
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);

    }

    @GetMapping("/owner/{ownerId}/products") // GET BY OWNER.
    public ResponseEntity<List<ProductDto>> getProductByOwner(@PathVariable Long ownerId) {
        List<ProductDto> productDtoList = this.productService.getPostByOwner(ownerId);
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @GetMapping("/pages") // GET ALL PRODUCTS.
    public ResponseEntity<List<ProductDto>> getPages(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize
    ) {
        List<ProductDto> productDtoList = this.productService.getPages(pageNumber, pageSize);
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/product/{productId}") // GET PRODUCT BY ID.
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        ProductDto productDto = this.productService.getProductById(productId);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @DeleteMapping("/product/{productId}") // DELETE PRODUCT
    public ResponseEntity<ApiMessage> deleteProduct(@PathVariable Long productId) {
        this.productService.deleteProduct(productId);
        return new ResponseEntity<>(new ApiMessage("Product Deleted Successfully", true), HttpStatus.OK);
    }

    @PutMapping("/product/{productId}") // UPDATE PRODUCT
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto
            , @PathVariable Long productId) {
        ProductDto updatedProductDto = this.productService.updateProduct(productDto, productId);
        return ResponseEntity.ok(updatedProductDto);
    }

    @GetMapping("/pagesInfo")
    public ResponseEntity<ProductResponse> getPagesInfo(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize
    ) {
        ProductResponse productResponse = this.productService.getPageInfo(pageNumber, pageSize);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);

    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtoList = this.productService.getAllProducts();
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @GetMapping("/sorting")
    public ResponseEntity<ProductResponse> sortingProducts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
    ) {
        ProductResponse productResponse = this.productService.sortingProducts(pageNumber, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/productName/search/{keyword}")
    public ResponseEntity<List<ProductDto>> searchProductByName(@PathVariable String keyword) {
        List<ProductDto> productDtoList = this.productService.searchProductName(keyword);
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/productModel/search/{keyword}")
    public ResponseEntity<List<ProductDto>> searchProductByModel(@PathVariable String keyword) {
        List<ProductDto> productDtoList = this.productService.searchProductModel(keyword);
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/productPrice/search/{price}")
    public ResponseEntity<List<ProductDto>> searchProductByPrice(@PathVariable Integer price) {
        List<ProductDto> productDtoList = this.productService.searchProductPrice(price);
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/productType/search/{keyword}")
    public ResponseEntity<List<ProductDto>> searchProductByType(@PathVariable String keyword) {
        List<ProductDto> productDtoList = this.productService.searchProductType(keyword);
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/productName/query/{keyword}")
    public ResponseEntity<List<ProductDto>> searchProductNameByQuery(@PathVariable String keyword) {
        List<ProductDto> productDtoList = this.productService.searchProductNameByQuery(keyword);
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }


    @PostMapping("/search")
    public ResponseEntity<List<ProductDto>> productSearch(@RequestBody ProductSearchDto productSearchDto) {
        List<ProductDto> products = this.productService.productSearch(productSearchDto);

        return ResponseEntity.ok(products);

    }


}
