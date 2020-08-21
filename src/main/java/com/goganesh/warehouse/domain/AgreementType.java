package com.goganesh.warehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(schema = "warehouse", name = "agreement_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgreementType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
