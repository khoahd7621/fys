package com.khoahd7621.youngblack.repositories;

import com.khoahd7621.youngblack.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
    public Optional<Category> findByName(String name);

}
