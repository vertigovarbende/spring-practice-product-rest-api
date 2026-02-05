package com.deveyk.northwind.employee.model.entity;

import com.deveyk.northwind.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "territories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Territory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "territory_id")
    private Long id;

    @Column(name = "territory_description", nullable = false)
    private String territoryDescription;

    @ManyToOne(fetch = FetchType.LAZY,
              cascade={CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
}
