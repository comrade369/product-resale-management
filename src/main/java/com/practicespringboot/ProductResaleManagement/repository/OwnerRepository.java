package com.practicespringboot.ProductResaleManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicespringboot.ProductResaleManagement.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
