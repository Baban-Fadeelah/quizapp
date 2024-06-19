package com.abdulwadud.quiz_app.controller;

import com.abdulwadud.quiz_app.model.QuestionWrapper;
import com.abdulwadud.quiz_app.model.Quiz;
import com.abdulwadud.quiz_app.model.Response;
import com.abdulwadud.quiz_app.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")

public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String examType, @RequestParam int numQ, @RequestParam String title) {
        return quizService.createQuiz(examType, numQ, title);

    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id) {
        return quizService.getQuizQuestions(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses) {
        return quizService.calculateResult(id, responses);
    }

    @GetMapping("/createQuiz")
    public String showCreateQuizForm(Model model) {
        model.addAttribute("quiz", new Quiz());
        return "createQuiz";
    }

    @PostMapping("/createQuiz")
    public String createQuiz(@ModelAttribute Quiz quiz) {
        // Save the quiz to the database
        return "redirect:/quizzes";
    }



//    public ResponseEntity<String> submitQuiz(@PathVariable int id) {}

}
