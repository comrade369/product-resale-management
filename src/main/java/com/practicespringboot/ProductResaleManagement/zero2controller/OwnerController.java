package com.practicespringboot.ProductResaleManagement.zero2controller;

import com.practicespringboot.ProductResaleManagement.zero1entity.Owner;
import com.practicespringboot.ProductResaleManagement.zero3service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/owners")
public class OwnerController {

    private OwnerService ownerService;

    // build create Owner REST API
    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        Owner savedOwner = ownerService.createOwner(owner);
        return new ResponseEntity<>(savedOwner, HttpStatus.CREATED);
    }

    // build get Owner by id REST API
    // http://localhost:8080/api/owners/1
    @GetMapping("{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable("id") Long ownerId) {
        Owner owner = ownerService.getOwnerById(ownerId);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    // build Get All Owners REST API
    // http://localhost:8080/api/owners

    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> owners = ownerService.getAllOwners();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    // build Update Owner REST API
    // http://localhost:8080/api/owners/1
    @PutMapping("{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable("id") Long ownerId
            , @RequestBody Owner owner) {
        owner.setOwnerId(ownerId);
        Owner updateOwner = ownerService.updateOwner(owner);
        return new ResponseEntity<>(updateOwner, HttpStatus.OK);
    }

    // build Delete Owner REST API

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long ownerId) {
        ownerService.deleteOwner(ownerId);
        return new ResponseEntity<>("Owner successfully deleted!", HttpStatus.OK);
    }

}
