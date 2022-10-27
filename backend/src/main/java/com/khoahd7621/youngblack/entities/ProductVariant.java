package com.khoahd7621.youngblack.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product_variant_tbl")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_variant_id")
    private long id;

    @Column(name = "sku", unique = true)
    private String sku;
    @Column(name = "is_in_stock")
    private boolean isInStock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "size_id")
    private Size size;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "color_id")
    private Color color;

    @OneToMany(mappedBy = "productVariant", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Image> images;
}
