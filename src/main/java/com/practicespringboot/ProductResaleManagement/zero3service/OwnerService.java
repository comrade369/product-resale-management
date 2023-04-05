package com.practicespringboot.ProductResaleManagement.zero3service;

import com.practicespringboot.ProductResaleManagement.zero1entity.Owner;

import java.util.List;

public interface OwnerService {
    Owner createOwner(Owner owner);

    Owner getOwnerById(Long ownerId);

    List<Owner> getAllOwners();

    Owner updateOwner(Owner owner);

    void deleteOwner(Long ownerId);
}
