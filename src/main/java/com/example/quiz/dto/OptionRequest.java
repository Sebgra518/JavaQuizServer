package com.example.quiz.dto;

import jakarta.validation.constraints.NotBlank;

public record OptionRequest(
    @NotBlank String optionText
) {}
