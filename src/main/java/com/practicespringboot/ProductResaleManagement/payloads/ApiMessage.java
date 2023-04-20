package com.practicespringboot.ProductResaleManagement.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiMessage {
    String message;
    Boolean successStatus;
}
