package com.goganesh.warehouse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "warehouse", name = "agreements")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "agreement_id")
    private List<Payment> payments;
}
