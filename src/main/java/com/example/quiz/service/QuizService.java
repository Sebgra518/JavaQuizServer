package com.example.quiz.service;

import com.example.quiz.dto.*;
import com.example.quiz.model.*;
import com.example.quiz.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class QuizService {
  private final CategoryRepo categories;
  private final QuestionRepo questions;
  private final OptionRepo options;

  public QuizService(CategoryRepo c, QuestionRepo q, OptionRepo o){
    this.categories = c; this.questions = q; this.options = o;
  }

  public Category addCategory(CategoryRequest req){
    var c = new Category();
    c.setCategoryName(req.categoryName());
    return categories.save(c);
  }

  public List<Category> listCategories(){ return categories.findAll(); }

  @Transactional
  public Question addQuestion(QuestionRequest req){
    var category = categories.findById(req.categoryId())
        .orElseThrow(() -> new IllegalArgumentException("Category not found"));

    var q = new Question();
    q.setCategory(category);
    q.setQuestionText(req.questionText());
    q = questions.save(q);

    var opts = req.options();
    if (opts.isEmpty()) throw new IllegalArgumentException("Options required");

    Option correct = null;
    for (int i=0; i<opts.size(); i++){
      var o = new Option();
      o.setQuestion(q);
      o.setOptionText(opts.get(i).optionText());
      o = options.save(o);
      if (i == req.correctIndex()) correct = o;
    }
    if (correct == null) throw new IllegalArgumentException("correctIndex out of range");
    q.setCorrectOption(correct);
    return questions.save(q);
  }

  public List<Question> listQuestionsByCategory(Long categoryId){
    return questions.findByCategory_CategoryId(categoryId);
  }
}
