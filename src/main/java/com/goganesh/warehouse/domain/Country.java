package com.goganesh.warehouse.domain;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(schema = "warehouse", name = "countries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
