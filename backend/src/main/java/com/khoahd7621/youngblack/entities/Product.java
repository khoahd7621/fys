package com.khoahd7621.youngblack.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "product_tbl")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<ProductVariant> productVariants;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "slug")
    private String slug;
    @Column(name = "price")
    private double price;
    @Column(name = "discount_price")
    private double discountPrice;
    @Column(name = "start_date_discount")
    private Date startDateDiscount;
    @Column(name = "end_date_discount")
    private Date endDateDiscount;
    @Column(name = "primary_cover_img_url")
    private String primaryImageUrl;
    @Column(name = "secondary_cover_img_url")
    private String secondaryImageUrl;
}
