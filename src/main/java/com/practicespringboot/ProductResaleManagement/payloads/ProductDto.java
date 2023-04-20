package com.practicespringboot.ProductResaleManagement.payloads;

import com.practicespringboot.ProductResaleManagement.zero1entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String productName;
    private String productType;
    private String productModel;
    private Integer productPrice;
    private Date productPurchaseDate;
    private OwnerDto owner;
}
