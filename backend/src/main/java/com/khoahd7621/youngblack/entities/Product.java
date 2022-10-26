package com.khoahd7621.youngblack.entities;

import com.khoahd7621.youngblack.constants.EProductSize;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product_tbl")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String sku;

    @Enumerated(EnumType.STRING)
    private EProductSize size;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "detail_id")
    private ProductDetail detail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "color_id")
    private Color color;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Image> images;

}
