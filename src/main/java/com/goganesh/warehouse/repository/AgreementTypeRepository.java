package com.goganesh.warehouse.repository;

import com.goganesh.warehouse.domain.AgreementType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "agreement_types", itemResourceRel = "agreement_type", path = "agreement_types")
public interface AgreementTypeRepository extends JpaRepository<AgreementType, Long> {
    Page<AgreementType> findAllByNameContaining(String name, Pageable pageRequest);
}
