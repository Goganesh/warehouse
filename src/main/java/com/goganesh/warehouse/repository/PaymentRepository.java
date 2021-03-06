package com.goganesh.warehouse.repository;

import com.goganesh.warehouse.domain.Agreement;
import com.goganesh.warehouse.domain.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "payments", itemResourceRel = "payment", path = "payments")
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Page<Payment> findAllByNameContaining(String name, Pageable pageRequest);
    Page<Payment> findAllByAgreementAndNameContaining(Agreement agreement, String name, Pageable pageRequest);
    List<Payment> findByAgreementId(Long id);
}
