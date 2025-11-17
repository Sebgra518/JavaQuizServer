package com.example.quiz.controller;

import com.example.quiz.dto.*;
import com.example.quiz.model.*;
import com.example.quiz.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api")
public class QuizController {
  private final QuizService svc;
  public QuizController(QuizService svc){ this.svc = svc; }

  // Categories
  @PostMapping("/categories")
  public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest req){
    var c = svc.addCategory(req);
    return new CategoryResponse(c.getCategoryId(), c.getCategoryName());
  }

  @GetMapping("/categories")
  public List<CategoryResponse> listCategories(){
    return svc.listCategories().stream()
      .map(c -> new CategoryResponse(c.getCategoryId(), c.getCategoryName()))
      .toList();
  }

  // Questions
  @PostMapping("/questions")
  public QuestionBrief createQuestion(@Valid @RequestBody QuestionRequest req){
    var q = svc.addQuestion(req);
    return new QuestionBrief(q.getQuestionId(), q.getCategory().getCategoryId(), q.getQuestionText());
  }

  @GetMapping("/categories/{categoryId}/questions")
  public List<QuestionBrief> listQuestions(@PathVariable Long categoryId){
    return svc.listQuestionsByCategory(categoryId).stream()
      .map(q -> new QuestionBrief(q.getQuestionId(), q.getCategory().getCategoryId(), q.getQuestionText()))
      .toList();
  }
}
