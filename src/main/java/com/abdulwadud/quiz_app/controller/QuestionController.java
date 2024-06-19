package com.abdulwadud.quiz_app.controller;

import com.abdulwadud.quiz_app.model.examQuestions;
import com.abdulwadud.quiz_app.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")

    public List<examQuestions> getAllQuestions() {
        return questionService.getAllQuestions();

    }

    @GetMapping("examType/{examType}")
    public List<examQuestions> getQuestionsByExamType(@PathVariable String examType) {
        return questionService.getQuestionsByExamType(examType);
    }

    @PostMapping("add")
    public String addQuestions( @RequestBody examQuestions examQuestions) {
        return questionService.addQuestions(examQuestions);
    }
}
