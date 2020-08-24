package com.goganesh.warehouse.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.goganesh.warehouse.deserializer.AgreementDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "warehouse", name = "agreements")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonDeserialize(using = AgreementDeserializer.class)
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agreement_type_id")
    private AgreementType agreementType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

    @Column(name = "start_date")
    private Date startDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "price")
    private long price;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "agreement_id")
    private List<Payment> payments;
}
