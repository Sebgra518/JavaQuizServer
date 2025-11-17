package com.example.quiz.dto;

public record QuestionBrief(
    Long questionId,
    Long categoryId,
    String questionText
) {}
