package com.khoahd7621.youngblack.repositories;

import com.khoahd7621.youngblack.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
}
