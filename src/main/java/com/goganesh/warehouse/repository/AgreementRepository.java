package com.goganesh.warehouse.repository;

import com.goganesh.warehouse.domain.Agreement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "agreements", itemResourceRel = "agreement", path = "agreements")
public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    Page<Agreement> findAllByNameContaining(String name, Pageable pageRequest);
}
