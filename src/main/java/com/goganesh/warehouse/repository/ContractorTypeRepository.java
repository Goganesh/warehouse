package com.goganesh.warehouse.repository;

import com.goganesh.warehouse.domain.ContractorType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "contractor_types", itemResourceRel = "contractor_type", path = "contractor_types")
public interface ContractorTypeRepository extends JpaRepository<ContractorType, Long> {
    Page<ContractorType> findAllByNameContaining(String name, Pageable pageRequest);
}