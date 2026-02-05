package com.deveyk.northwind.employee.model.entity;

import com.deveyk.northwind.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cacheable
public class Employee extends BaseEntity implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_seq")
    @SequenceGenerator(name = "employees_seq", sequenceName = "employees_seq", allocationSize = 1, initialValue = 1000)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "title")
    private String title;

    @Column(name = "title_of_courtesy")
    private String titleOfCourtesy;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "salary")
    private BigDecimal salary;

    @ManyToOne(fetch = FetchType.EAGER, // LAZY??
            cascade={CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "reports_to")
    private Employee reportsTo;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmployeeDetails employeeDetails;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade={CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "employee_territories",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "territory_id")
    )
    private Set<Territory> territory;

}
