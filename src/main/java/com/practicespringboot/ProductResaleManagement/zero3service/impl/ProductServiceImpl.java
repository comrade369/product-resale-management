package com.practicespringboot.ProductResaleManagement.zero3service.impl;

import com.practicespringboot.ProductResaleManagement.dto.ProductSearchDto;
import com.practicespringboot.ProductResaleManagement.dto.ProductSpecification;
import com.practicespringboot.ProductResaleManagement.dto.SearchCriteria;
import com.practicespringboot.ProductResaleManagement.zero1entity.Product;
import com.practicespringboot.ProductResaleManagement.zero4repository.ProductRepository;
import com.practicespringboot.ProductResaleManagement.zero3service.ProductService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
        public Product createProduct(Product product) {
            try {
                return productRepository.save(product);
            } catch(Exception e) {
                e.printStackTrace();
                throw e;
            }
        }

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getId()).get();
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductModel(product.getProductModel());
        existingProduct.setProductOwner(product.getProductOwner());
        existingProduct.setProductPrice(product.getProductPrice());
        existingProduct.setProductType(product.getProductType());
        existingProduct.setProductPurchaseDate(product.getProductPurchaseDate());
        Product updatedProduct = productRepository.save(existingProduct);
        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> productSearch(ProductSearchDto productSearchRequest) {

        List<Product> results = new ArrayList<>();
        List<ProductSpecification> allSpecs = new ArrayList<>();

        if(productSearchRequest.getProductName()!= null &&
                !productSearchRequest.getProductName().trim().isEmpty())
        {

            ProductSpecification  productNameSpec =     new ProductSpecification(new SearchCriteria(
                    "productName", ":", productSearchRequest.getProductName()));
            allSpecs.add(productNameSpec);
        }

        if(productSearchRequest.getProductType()!= null &&
                !productSearchRequest.getProductType().trim().isEmpty())
        {
            ProductSpecification   productTypeSpec =     new ProductSpecification(new SearchCriteria(
                    "productType", ":", productSearchRequest.getProductType()));
            allSpecs.add(productTypeSpec);
        }
        if(productSearchRequest.getProductModel() != null &&
                !productSearchRequest.getProductModel().trim().isEmpty()) {
            ProductSpecification productModelSpec = new ProductSpecification(new SearchCriteria(
                    "productModel", ":", productSearchRequest.getProductModel()));
            allSpecs.add(productModelSpec);

        }
        if (productSearchRequest.getProductPrice() != 0) {
            ProductSpecification productPriceSpec = new ProductSpecification(new SearchCriteria(
                    "productPrice", ":", productSearchRequest.getProductPrice()));
            allSpecs.add(productPriceSpec);
        }
        if (productSearchRequest.getProductOwner() != null &&
                !productSearchRequest.getProductOwner().trim().isEmpty()) {
            ProductSpecification productOwnerSpec = new ProductSpecification(new SearchCriteria(
                    "productOwner.ownerName", ":", productSearchRequest.getProductOwner()));
            allSpecs.add(productOwnerSpec);
        }


        if(!allSpecs.isEmpty()) {
            Specification<Product> specifications = Specification.where(allSpecs.get(0));
            for(ProductSpecification spec: allSpecs) {
                specifications  = specifications.and(spec);
            }

         results = productRepository.findAll(specifications);

        } else {
            results=  productRepository.findAll();
        }
        return results;
    }
}
