package com.goganesh.warehouse.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.goganesh.warehouse.deserializer.ContractorDeserializer;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(schema = "warehouse", name = "contractors")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "phone_number")
    private long phoneNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contractor_type_id")
    private ContractorType contractorType;
}
