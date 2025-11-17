package com.example.quiz.model;

import jakarta.persistence.*;

@Entity
@Table(name = "options")
public class Option {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long optionId;

  @ManyToOne(optional = false)
  @JoinColumn(name = "question_id")
  private Question question;

  @Column(nullable = false, length = 255)
  private String optionText;

  // getters/setters
  public Long getOptionId() { return optionId; }
  public void setOptionId(Long optionId) { this.optionId = optionId; }

  public Question getQuestion() { return question; }
  public void setQuestion(Question question) { this.question = question; }

  public String getOptionText() { return optionText; }
  public void setOptionText(String optionText) { this.optionText = optionText; }
}
