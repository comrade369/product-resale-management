package com.practicespringboot.ProductResaleManagement.dto;

import com.practicespringboot.ProductResaleManagement.zero1entity.Owner;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

}
