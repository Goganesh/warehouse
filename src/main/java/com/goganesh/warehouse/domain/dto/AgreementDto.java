package com.goganesh.warehouse.domain.dto;

import com.goganesh.warehouse.domain.Agreement;
import com.goganesh.warehouse.domain.AgreementType;
import com.goganesh.warehouse.domain.Contractor;
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

    private Date endDate;

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
                .endDate(agreement.getEndDate())
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
                .endDate(agreementDto.getEndDate())
                .price(agreementDto.getPrice())
                .build();
    }

}
