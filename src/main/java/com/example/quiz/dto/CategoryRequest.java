package com.example.quiz.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
    @NotBlank String categoryName
) {}
