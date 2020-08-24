package com.goganesh.warehouse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@Table(schema = "warehouse", name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    private String password;

    private String surname;

    private String name;

    private String patronymic;

    private String email;

    private boolean enabled;

    public String getFullName(){
        return surname + name + patronymic;
    }
}
