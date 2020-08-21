package com.goganesh.warehouse.repository;

import com.goganesh.warehouse.domain.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "countries", itemResourceRel = "country", path = "countries")
public interface CountryRepository extends JpaRepository<Country, Long> {
    Page<Country> findAllByNameContaining(String name, Pageable pageRequest);
}
