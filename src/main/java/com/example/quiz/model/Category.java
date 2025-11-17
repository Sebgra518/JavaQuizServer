package com.example.quiz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long categoryId;

  @NotBlank
  @Column(nullable = false)
  private String categoryName;

  // getters/setters
  public Long getCategoryId() { return categoryId; }
  public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

  public String getCategoryName() { return categoryName; }
  public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
