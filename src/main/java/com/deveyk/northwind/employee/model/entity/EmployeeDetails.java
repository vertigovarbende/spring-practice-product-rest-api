package com.deveyk.northwind.employee.model.entity;

import com.deveyk.northwind.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "employee_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cacheable
public class EmployeeDetails extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Employee employee;

    @Column(name = "hire_date")
    private LocalDate hireDate;

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

    @Column(name = "home_phone")
    private String homePhone;

    @Column(name = "extension")
    private String extension;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
}
