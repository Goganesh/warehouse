package com.goganesh.warehouse.domain.dto;

import com.goganesh.warehouse.domain.*;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class AgreementDto {

    private Long id;

    private String name;

    private AgreementType agreementType;

    private Contractor contractor;

    private Date startDate;

    private User user;

    private long price;

    private int countPayments;

    private long sumPayments;

    public static AgreementDto toDto(Agreement agreement) {
        return AgreementDto.builder()
                .id(agreement.getId())
                .name(agreement.getName())
                .agreementType(agreement.getAgreementType())
                .contractor(agreement.getContractor())
                .startDate(agreement.getStartDate())
                .user(agreement.getUser())
                .price(agreement.getPrice())
                .countPayments(agreement.getPayments().size())
                .sumPayments(agreement.getPayments()
                        .stream()
                        .mapToLong(Payment::getAmount)
                        .sum())
                .build();
    }
}
