package com.practicespringboot.ProductResaleManagement.zero3service;

import com.practicespringboot.ProductResaleManagement.payloads.OwnerDto;
import com.practicespringboot.ProductResaleManagement.zero1entity.Owner;

import java.util.List;

public interface OwnerService {
    OwnerDto createOwner(OwnerDto ownerDto);

    OwnerDto getOwnerById(Long ownerId);

    List<OwnerDto> getAllOwners();

    OwnerDto updateOwner(OwnerDto ownerDto, Long id);

    void deleteOwner(Long ownerId);

}
