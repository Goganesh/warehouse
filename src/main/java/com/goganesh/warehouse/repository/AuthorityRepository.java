package com.goganesh.warehouse.repository;

import com.goganesh.warehouse.domain.Authority;
import com.goganesh.warehouse.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findByUser(User user);
}