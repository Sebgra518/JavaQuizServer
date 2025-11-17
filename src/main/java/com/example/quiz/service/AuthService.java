package com.example.quiz.service;

import com.example.quiz.dto.*;
import com.example.quiz.model.User;
import com.example.quiz.repo.UserRepo;
import com.example.quiz.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final UserRepo users;
  private final BCryptPasswordEncoder encoder;
  private final JwtService jwt;

  public AuthService(UserRepo users, BCryptPasswordEncoder encoder, JwtService jwt) {
    this.users = users; this.encoder = encoder; this.jwt = jwt;
  }

  public void register(RegisterRequest req) {
    users.findByUsername(req.username()).ifPresent(u -> {
      throw new IllegalArgumentException("Username already exists");
    });
    User u = new User();
    u.setUsername(req.username());
    u.setPasswordHash(encoder.encode(req.password()));
    users.save(u);
  }

  public String login(LoginRequest req) {
    var u = users.findByUsername(req.username())
        .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
    if (!encoder.matches(req.password(), u.getPasswordHash()))
      throw new IllegalArgumentException("Invalid credentials");
    return jwt.issue(u.getUsername());
  }
}
