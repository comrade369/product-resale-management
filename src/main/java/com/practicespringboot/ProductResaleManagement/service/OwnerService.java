package com.practicespringboot.ProductResaleManagement.service;

import com.practicespringboot.ProductResaleManagement.entity.Owner;
import com.practicespringboot.ProductResaleManagement.payloads.OwnerDto;

import java.util.List;

public interface OwnerService {
    OwnerDto createOwner(OwnerDto ownerDto);

    OwnerDto getOwnerById(Long ownerId);

    List<OwnerDto> getAllOwners();

    OwnerDto updateOwner(OwnerDto ownerDto, Long id);

    void deleteOwner(Long ownerId);

}
