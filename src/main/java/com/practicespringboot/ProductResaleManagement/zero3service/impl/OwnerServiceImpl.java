package com.practicespringboot.ProductResaleManagement.zero3service.impl;

import com.practicespringboot.ProductResaleManagement.zero1entity.Owner;
import com.practicespringboot.ProductResaleManagement.zero3service.OwnerService;
import com.practicespringboot.ProductResaleManagement.zero4repository.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private OwnerRepository ownerRepository;

    @Override
    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner updateOwner(Owner owner) {
        Owner existingOwner = ownerRepository.findById(owner.getOwnerId()).get();
        existingOwner.setOwnerName(owner.getOwnerName());
        existingOwner.setOwnerEmail(owner.getOwnerEmail());
        existingOwner.setOwnerAddress(owner.getOwnerAddress());
        existingOwner.setOwnerPhone(owner.getOwnerPhone());
        existingOwner.setOwnerPinCode(owner.getOwnerPinCode());
        Owner updateOwner = ownerRepository.save(existingOwner);
        return updateOwner;
    }

    @Override
    public void deleteOwner(Long ownerId) {
        ownerRepository.deleteById(ownerId);
    }

    @Override
    public Owner getOwnerById(Long ownerId) {
        Optional<Owner> optionalOwner = ownerRepository.findById(ownerId);
        return optionalOwner.get();
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }
}
