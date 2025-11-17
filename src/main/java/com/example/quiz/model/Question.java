package com.example.quiz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "questions")
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long questionId;

  @ManyToOne(optional = false)
  @JoinColumn(name = "category_id")
  private Category category;

  @NotBlank
  @Column(nullable = false, columnDefinition = "TEXT")
  private String questionText;

  @OneToOne
  @JoinColumn(name = "correct_option_id")
  private Option correctOption;

  // getters/setters
  public Long getQuestionId() { return questionId; }
  public void setQuestionId(Long questionId) { this.questionId = questionId; }

  public Category getCategory() { return category; }
  public void setCategory(Category category) { this.category = category; }

  public String getQuestionText() { return questionText; }
  public void setQuestionText(String questionText) { this.questionText = questionText; }

  public Option getCorrectOption() { return correctOption; }
  public void setCorrectOption(Option correctOption) { this.correctOption = correctOption; }
}
