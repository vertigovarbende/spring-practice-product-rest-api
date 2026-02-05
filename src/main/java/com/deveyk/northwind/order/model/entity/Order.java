package com.deveyk.northwind.order.model.entity;

import com.deveyk.northwind.common.entity.BaseEntity;
import com.deveyk.northwind.customer.model.entity.Customer;
import com.deveyk.northwind.employee.model.entity.Employee;
import com.deveyk.northwind.order.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cacheable
public class Order extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    @SequenceGenerator(name = "orders_seq", sequenceName = "orders_seq", allocationSize = 1, initialValue = 1000)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade={CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade={CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "required_date")
    private LocalDateTime requiredDate;

    @Column(name = "shipped_date")
    private LocalDateTime shippedDate;

    @Column(name = "freight")
    private Long freight;

    @Column(name = "ship_name")
    private String shipName;

    @Column(name = "ship_address")
    private String shipAddress;

    @Column(name = "ship_city")
    private String shipCity;

    @Column(name = "ship_region")
    private String shipRegion;

    @Column(name = "ship_postal_code")
    private String shipPostalCode;

    @Column(name = "ship_country")
    private String shipCountry;



    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<OrderDetails> orderDetails;

}
