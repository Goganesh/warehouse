package com.goganesh.warehouse.domain.dto;

import com.goganesh.warehouse.domain.Agreement;
import com.goganesh.warehouse.domain.AgreementType;
import com.goganesh.warehouse.domain.Contractor;
import com.goganesh.warehouse.domain.User;
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
                        .mapToLong(payment -> payment.getAmount())
                        .sum())
                .build();
    }
    public static Agreement toEntity(AgreementDto agreementDto) {
        return Agreement.builder()
                .id(agreementDto.getId())
                .name(agreementDto.getName())
                .agreementType(agreementDto.getAgreementType())
                .contractor(agreementDto.getContractor())
                .startDate(agreementDto.getStartDate())
                .user(agreementDto.user)
                .price(agreementDto.getPrice())
                .build();
    }

}
