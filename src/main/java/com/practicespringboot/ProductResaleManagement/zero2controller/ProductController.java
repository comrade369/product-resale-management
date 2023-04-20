package com.practicespringboot.ProductResaleManagement.zero2controller;

import com.practicespringboot.ProductResaleManagement.payloads.ApiMessage;
import com.practicespringboot.ProductResaleManagement.payloads.ProductDto;
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


//    private OwnerService ownerService;

    // build create product REST API
    @PostMapping("/owner/{ownerId}/products") // CREATE - POST
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto
            , @PathVariable Long ownerId) {
        ProductDto productDto1 = this.productService.createProduct(productDto, ownerId);
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);

//        boolean isValidOwner = false;
//        if(product != null) {
//
//            if(product.getProductOwner() != null && product.getProductOwner().getOwnerId() > 0) {
//                isValidOwner = ownerService.isValidOwnerId(product.getProductOwner().getOwnerId());
//
//            }
//        }
//
//        if(isValidOwner) {
//            Product savedProduct = productService.createProduct(product);
//            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<>("Invalid Owner, please provide valid owener id", HttpStatus.BAD_REQUEST);
//        }

    }

    @GetMapping("/owner/{ownerId}/products") // GET BY OWNER.
    public ResponseEntity<List<ProductDto>> getProductByOwner(@PathVariable Long ownerId) {
        List<ProductDto> productDtoList = this.productService.getPostByOwner(ownerId);
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @GetMapping("/products") // GET ALL PRODUCTS.
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtoList = this.productService.getAllProducts();
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

    // build get product by id REST API
    // http://localhost:8080/api/products/1
//    @GetMapping("{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable("id") Long productId) {
//        Product product = productService.getProductById(productId);
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }

    // build get all products REST API
    // http://localhost:8080/api/products
//    @GetMapping
//    public ResponseEntity<List<Product>> getAllProducts() {
//        List<Product> products = productService.getAllProducts();
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }
//    @PostMapping("/search")
//    public ResponseEntity<List<Product>> productSearch(@RequestBody ProductSearchDto productSearchRequest) {
//
//        List<Product> products = productService.productSearch(productSearchRequest);
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }
//    // build update product REST API
//    @PutMapping("{id}")
//    //http://localhost:8080/api/products/1
//    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long productId,
//                                                 @RequestBody Product product) {
//        product.setId(productId);
//        Product updateProduct = productService.updateProduct(product);
//        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
//    }
//
//    // build Delete User REST API ;
//    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId) {
//        productService.deleteProduct(productId);
//        return new ResponseEntity<>("Product successfully deleted!", HttpStatus.OK);
//    }
}
