package com.khoahd7621.youngblack.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "product_detail_tbl")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {
    @OneToMany(mappedBy = "detail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Product> products;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
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
