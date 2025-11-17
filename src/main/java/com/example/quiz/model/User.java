package com.example.quiz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @NotBlank
  @Column(nullable = false, unique = true)
  private String username;

  @NotBlank
  @Column(nullable = false)
  private String passwordHash;

  // getters/setters
  public Long getUserId() { return userId; }
  public void setUserId(Long userId) { this.userId = userId; }

  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }

  public String getPasswordHash() { return passwordHash; }
  public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}
