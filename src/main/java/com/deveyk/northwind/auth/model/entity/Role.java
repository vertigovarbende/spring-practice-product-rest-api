package com.deveyk.northwind.auth.model.entity;

import com.deveyk.northwind.auth.model.enums.RoleType;
import com.deveyk.northwind.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleType name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;


}
