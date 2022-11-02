package com.khoahd7621.youngblack.repositories;

import com.khoahd7621.youngblack.entities.Rating;
import com.khoahd7621.youngblack.entities.compositekey.RatingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, RatingKey> {
    public Optional<Rating> findByRatingId(RatingKey ratingId);
}
