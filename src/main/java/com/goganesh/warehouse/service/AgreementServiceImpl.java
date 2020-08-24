package com.goganesh.warehouse.service;

import com.goganesh.warehouse.domain.Agreement;
import com.goganesh.warehouse.domain.User;
import com.goganesh.warehouse.repository.AgreementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;

    @Override
    public List<Agreement> findExpiredAgreementsByUser(User user) {
        List<Agreement> userAgreements = agreementRepository.findByUser(user);
        return null;
    }

    private boolean isPaid(Agreement agreement){
        boolean isPaid = true;
        long price = agreement.getPrice();
        long paymentAmountSum = agreement.getPayments()
                .stream()
                .mapToLong(payment -> payment.getAmount())
                .sum();
        if (price < paymentAmountSum)
            isPaid = false;

        return isPaid;
    }
}
