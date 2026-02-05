package com.deveyk.northwind.product.model.entity;

import com.deveyk.northwind.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cacheable
public class Supplier extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_title")
    private String contactTitle;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "region")
    private String region;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "homepage")
    private String homepage;


}
