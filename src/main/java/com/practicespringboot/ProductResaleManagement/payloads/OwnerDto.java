package com.practicespringboot.ProductResaleManagement.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {
    private Long ownerId;

    @NotEmpty(message = "Field must be filled !!!")
    private String ownerName;

    @NotEmpty(message = "Field must be email address !!!")
    @Email
    private String ownerEmail;

    @NotEmpty(message = "Must give an address !!!")
    private String ownerAddress;

    private Long ownerPhone;

    @Size(min = 6)
    @NotEmpty(message = "Pin code must be valid !!!")
    private String ownerPinCode;
}
