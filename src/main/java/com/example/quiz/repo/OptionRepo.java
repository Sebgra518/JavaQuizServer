package com.example.quiz.repo;

import com.example.quiz.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepo extends JpaRepository<Option, Long> {
  List<Option> findByQuestion_QuestionId(Long questionId);
}
