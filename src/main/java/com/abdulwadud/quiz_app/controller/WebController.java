package com.abdulwadud.quiz_app.controller;

import com.abdulwadud.quiz_app.model.QuestionWrapper;
import com.abdulwadud.quiz_app.model.Quiz;
import com.abdulwadud.quiz_app.model.Response;
import com.abdulwadud.quiz_app.service.QuestionService;
import com.abdulwadud.quiz_app.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/web/quiz")
public class WebController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/create")
    public String createQuizForm(Model model) {
        model.addAttribute("quiz", new Quiz());
        return "createQuiz";
    }

    @PostMapping("/create")
    public String createQuiz(@RequestParam String examType, @RequestParam int numQ, @RequestParam String title, Model model) {
        quizService.createQuiz(examType, numQ, title);
        return "redirect:/web/quiz/list";
    }

    @GetMapping("/list")
    public String listQuizzes(Model model) {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        model.addAttribute("quizzes", quizzes);
        return "listQuizzes";
    }

    @GetMapping("/view/{id}")
    public String viewQuiz(@PathVariable int id, Model model) {
        List<QuestionWrapper> questions = quizService.getQuizQuestions(id).getBody();
        Quiz quiz = quizService.getQuizById(id);
        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", questions);
        return "viewQuiz";
    }
    @PostMapping("/submit/{id}")
    public String submitQuiz(@PathVariable int id, @RequestParam MultiValueMap<String, String> formData, Model model) {
        List<Response> responses = new ArrayList<>();

        formData.forEach((key, value) -> {
            // Example key format: "responses[13]", extract "13" from it
            if (key.startsWith("responses[")) {
                int index = Integer.parseInt(key.substring(key.indexOf('[') + 1, key.indexOf(']')));
                responses.add(new Response(index, value.get(0)));
            }
        });

        int score = quizService.calculateResult(id, responses).getBody();
        model.addAttribute("score", score);
        return "result";
    }


//    @PostMapping("/submit/{id}")
//    public String submitQuiz(@PathVariable int id, @RequestParam MultiValueMap<String, String> formData, Model model) {
//        List<Response> responses = new ArrayList<>();
//        formData.forEach((key, value) -> responses.add(new Response(Integer.parseInt(key), value.get(0))));
//        int score = quizService.calculateResult(id, responses).getBody();
//        model.addAttribute("score", score);
//        return "result";
//    }
}
