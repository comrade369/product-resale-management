package com.practicespringboot.ProductResaleManagement.zero4repository;

import com.practicespringboot.ProductResaleManagement.zero1entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
