package com.khoahd7621.youngblack.repositories;

import com.khoahd7621.youngblack.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Optional<Product> findByName(String name);

    public Page<Product> findAllByIsDeletedFalse(Pageable pageable);
}
