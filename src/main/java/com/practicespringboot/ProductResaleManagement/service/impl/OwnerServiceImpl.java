package com.practicespringboot.ProductResaleManagement.service.impl;

import com.practicespringboot.ProductResaleManagement.payloads.OwnerDto;
import com.practicespringboot.ProductResaleManagement.repository.OwnerRepository;
import com.practicespringboot.ProductResaleManagement.service.OwnerService;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.practicespringboot.ProductResaleManagement.entity.Owner;
import com.practicespringboot.ProductResaleManagement.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OwnerDto createOwner(OwnerDto ownerDto) {
        Owner owner = this.modelMapper.map(ownerDto, Owner.class);
        Owner addOwner = this.ownerRepository.save(owner);
        return this.modelMapper.map(addOwner, OwnerDto.class);
    }

    @Override
    public OwnerDto updateOwner(OwnerDto ownerDto, Long id) {
        Owner owner = this.ownerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Owner", "ownerId", id));
        owner.setOwnerName(ownerDto.getOwnerName());
        owner.setOwnerAddress(ownerDto.getOwnerAddress());
        owner.setOwnerEmail(ownerDto.getOwnerEmail());
        owner.setOwnerPhone(ownerDto.getOwnerPhone());
        owner.setOwnerPinCode(ownerDto.getOwnerPinCode());
        Owner owner1 = this.ownerRepository.save(owner);
        return this.modelMapper.map(owner1, OwnerDto.class);

    }

    @Override
    public void deleteOwner(Long ownerId) {
        Owner owner = this.ownerRepository.findById(ownerId).orElseThrow(() ->
                new ResourceNotFoundException("Owner", "ownerId", ownerId));
        this.ownerRepository.delete(owner);
    }

    @Override
    public OwnerDto getOwnerById(Long ownerId) {
        Owner owner = this.ownerRepository.findById(ownerId).orElseThrow(() ->
                new ResourceNotFoundException("Owner", "ownerId", ownerId));
        return this.modelMapper.map(owner, OwnerDto.class);
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        List<Owner> ownerList = this.ownerRepository.findAll();
        List<OwnerDto> ownerDtoList = new ArrayList<>();
        for (Owner ele : ownerList) {
            ownerDtoList.add(this.modelMapper.map(ele, OwnerDto.class));
        }
        return ownerDtoList;
    }

}
