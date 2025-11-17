package com.example.quiz.security;

import com.example.quiz.repo.UserRepo;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {
  private final JwtService jwt;
  private final UserRepo users;

  public JwtAuthFilter(JwtService jwt, UserRepo users) {
    this.jwt = jwt; this.users = users;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws ServletException, IOException {
    String auth = req.getHeader(HttpHeaders.AUTHORIZATION);
    if (StringUtils.hasText(auth) && auth.startsWith("Bearer ")) {
      try {
        String username = jwt.subject(auth.substring(7));
        users.findByUsername(username).ifPresent(u -> {
          var authTok = new UsernamePasswordAuthenticationToken(username, null, java.util.List.of());
          SecurityContextHolder.getContext().setAuthentication(authTok);
        });
      } catch (Exception ignored) {}
    }
    chain.doFilter(req, res);
  }
}
