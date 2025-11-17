package com.example.quiz.repo;

import com.example.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question, Long> {
  List<Question> findByCategory_CategoryId(Long categoryId);
}
