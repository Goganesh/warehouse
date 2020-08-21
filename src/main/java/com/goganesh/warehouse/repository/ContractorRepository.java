package com.goganesh.warehouse.repository;

import com.goganesh.warehouse.domain.Contractor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "contractors", itemResourceRel = "contractor", path = "contractors")
public interface ContractorRepository extends JpaRepository<Contractor, Long> {
    Page<Contractor> findAllByNameContaining(String name, Pageable pageRequest);
}
