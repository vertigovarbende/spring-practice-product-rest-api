package com.deveyk.northwind.product.model.entity;

import com.deveyk.northwind.order.model.entity.OrderDetails;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cacheable
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_seq")
    @SequenceGenerator(name = "products_seq", sequenceName = "products_seq", allocationSize = 1, initialValue = 1000)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name", nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, // LAZY??
               cascade={CascadeType.DETACH, CascadeType.MERGE,
                        CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.EAGER, // LAZY??
            cascade={CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "barcode", nullable = false)
    private String barcode;

    @Column(name = "quantity_per_unit")
    private String quantityPerUnit;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "units_in_stock")
    private Long unitsInStock;  // 10

    @Column(name = "units_on_order")
    private Long unitsOnOrder;  // 20

    @Column(name = "reorder_level")
    private Long reorderLevel;

    @Column(name = "discontinued", nullable = false)
    private boolean discontinued; // not wrapper class

    @OneToMany(mappedBy = "product")
    private Set<OrderDetails> orderDetails;

}
