package com.goganesh.warehouse.service;

import com.goganesh.warehouse.domain.Agreement;
import com.goganesh.warehouse.domain.User;

import java.util.List;

public interface AgreementService {
    List<Agreement> findExpiredAgreementsByUser(User user);
}
