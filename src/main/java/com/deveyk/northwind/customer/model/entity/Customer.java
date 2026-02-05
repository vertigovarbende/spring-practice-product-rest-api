package com.deveyk.northwind.customer.model.entity;

import com.deveyk.northwind.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cacheable
public class Customer extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -1115486003719601418L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(
            name = "customers_seq",
            sequenceName = "customers_seq",
            allocationSize = 1,
            initialValue = 1000
    )
    @Column(name = "customer_id")
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

    @Column(name = "username", nullable = false, unique = true)
    private String username;

}
