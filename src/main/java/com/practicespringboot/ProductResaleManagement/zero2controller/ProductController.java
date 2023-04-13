package com.practicespringboot.ProductResaleManagement.zero2controller;

import com.practicespringboot.ProductResaleManagement.dto.ProductSearchDto;
import com.practicespringboot.ProductResaleManagement.zero1entity.Product;
import com.practicespringboot.ProductResaleManagement.zero3service.OwnerService;
import com.practicespringboot.ProductResaleManagement.zero3service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/products")
public class ProductController {
    private ProductService productService;

    private OwnerService ownerService;

    // build create product REST API
    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        boolean isValidOwner = false;
        if(product != null) {

            if(product.getProductOwner() != null && product.getProductOwner().getOwnerId() > 0) {
                isValidOwner = ownerService.isValidOwnerId(product.getProductOwner().getOwnerId());

            }
        }

        if(isValidOwner) {
            Product savedProduct = productService.createProduct(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid Owner, please provide valid owener id", HttpStatus.BAD_REQUEST);
        }

    }

    // build get product by id REST API
    // http://localhost:8080/api/products/1
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long productId) {
        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // build get all products REST API
    // http://localhost:8080/api/products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PostMapping("/search")
    public ResponseEntity<List<Product>> productSearch(@RequestBody ProductSearchDto productSearchRequest) {

        List<Product> products = productService.productSearch(productSearchRequest);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    // build update product REST API
    @PutMapping("{id}")
    //http://localhost:8080/api/products/1
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long productId,
                                                 @RequestBody Product product) {
        product.setId(productId);
        Product updateProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    // build Delete User REST API ;
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product successfully deleted!", HttpStatus.OK);
    }
}
