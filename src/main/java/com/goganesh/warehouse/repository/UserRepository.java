package com.goganesh.warehouse.repository;

import com.goganesh.warehouse.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", itemResourceRel = "user", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByNameContaining(String name, Pageable pageRequest);
    User findByUsername(String username);
}
