package com.practicespringboot.ProductResaleManagement.zero2controller;

import com.practicespringboot.ProductResaleManagement.payloads.ApiMessage;
import com.practicespringboot.ProductResaleManagement.payloads.ProductDto;
import com.practicespringboot.ProductResaleManagement.payloads.ProductResponse;
import com.practicespringboot.ProductResaleManagement.payloads.ProductSearchDto;
import com.practicespringboot.ProductResaleManagement.zero1entity.Product;
import com.practicespringboot.ProductResaleManagement.zero3service.OwnerService;
import com.practicespringboot.ProductResaleManagement.zero3service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/")
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

}
