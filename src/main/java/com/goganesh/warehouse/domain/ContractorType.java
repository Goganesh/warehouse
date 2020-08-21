package com.goganesh.warehouse.domain;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(schema = "warehouse", name = "contractor_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractorType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}
