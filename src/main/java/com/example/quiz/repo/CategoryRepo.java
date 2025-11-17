package com.example.quiz.repo;

import com.example.quiz.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {
  Optional<Category> findByCategoryName(String name);
}
