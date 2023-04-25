package com.practicespringboot.ProductResaleManagement.controller;

import com.practicespringboot.ProductResaleManagement.entity.Owner;
import com.practicespringboot.ProductResaleManagement.payloads.ApiMessage;
import com.practicespringboot.ProductResaleManagement.payloads.OwnerDto;
import com.practicespringboot.ProductResaleManagement.service.OwnerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    // build create Owner REST API
    @PostMapping("/")
    public ResponseEntity<OwnerDto> createOwner(@Valid @RequestBody OwnerDto ownerDto) {
        OwnerDto savedOwner = ownerService.createOwner(ownerDto);
        return new ResponseEntity<>(savedOwner, HttpStatus.CREATED);
    }

    // build get Owner by id REST API
    // http://localhost:8080/api/owners/1
    @GetMapping("{id}")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable("id") Long ownerId) {
        OwnerDto owner = ownerService.getOwnerById(ownerId);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    // build Get All Owners REST API
    // http://localhost:8080/api/owners

    @GetMapping("/")
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        List<OwnerDto> owners = ownerService.getAllOwners();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    // build Update Owner REST API
    // http://localhost:8080/api/owners/1
    @PutMapping("{id}")
    public ResponseEntity<OwnerDto> updateOwner(@Valid @PathVariable("id") Long ownerId
            , @RequestBody OwnerDto ownerDto) {
        OwnerDto updatedOwner = this.ownerService.updateOwner(ownerDto, ownerId);
        return ResponseEntity.ok(updatedOwner);

    }

    // build Delete Owner REST API

    @DeleteMapping("{id}")
    public ResponseEntity<ApiMessage> deleteUser(@PathVariable("id") Long ownerId) {
        this.ownerService.deleteOwner(ownerId);
        return new ResponseEntity<ApiMessage>(new ApiMessage("Owner Deleted Successfully", true), HttpStatus.OK);
    }

}
