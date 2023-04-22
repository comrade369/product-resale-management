package com.practicespringboot.ProductResaleManagement.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchDto {

    private String productName;


    private String productType;

    private String productModel;


    private String productOwner;

    private int productPrice;

    private int productPriceLowerLimit;

    private int productPriceUpperLimit;

}
