package com.khoahd7621.youngblack.entities.compositekey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RatingKey implements Serializable {
    @Column(name = "user_id")
    private long userId;
    @Column(name = "product_id")
    private int productId;
}
