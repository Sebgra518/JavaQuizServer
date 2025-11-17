package com.example.quiz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record QuestionRequest(
    @NotNull Long categoryId,
    @NotBlank String questionText,
    @NotNull List<OptionRequest> options,
    @NotNull Integer correctIndex // 0-based index
) {}
