package com.khoahd7621.youngblack.entities;

import com.khoahd7621.youngblack.entities.compositekey.RatingKey;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_rating_tbl")
public class Rating {

    @EmbeddedId
    private RatingKey ratingId;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "number_of_stars")
    private int stars;
    @Column(name = "comment")
    private String comment;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "is_show")
    private boolean isShow;
}
