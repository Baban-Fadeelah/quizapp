package com.abdulwadud.quiz_app.service;

import com.abdulwadud.quiz_app.model.QuestionWrapper;
import com.abdulwadud.quiz_app.model.Quiz;
import com.abdulwadud.quiz_app.model.Response;
import com.abdulwadud.quiz_app.dao.QuestionDao;
import com.abdulwadud.quiz_app.dao.QuizDao;
import com.abdulwadud.quiz_app.model.examQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String examType, int numQ, String title) {

        List<examQuestions> questions = questionDao.findRandomQuestionByExamType(examType, numQ);
      //  int numq = numQ;

        Quiz quiz = new Quiz();
        quiz.setExamType(examType);
        quiz.setNumQ(numQ);
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDao.save(quiz);
        return ResponseEntity.ok("Quiz created");

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<examQuestions> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUsers = new ArrayList<>();
        for (examQuestions q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionText(), q.getOption1(), q.getOption2(), q.getOption3());
            questionForUsers.add(qw);
        }
        return ResponseEntity.ok(questionForUsers);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        List<examQuestions> questionsFromDB = quiz.getQuestions();
        int right = 0;

        // Iterate over responses safely
        for (int i = 0; i < responses.size() && i < questionsFromDB.size(); i++) {
            Response response = responses.get(i);
            examQuestions question = questionsFromDB.get(i);

            // Example check: Ensure response and question properties are not null before comparison
            if (response != null && question != null && response.getResponse() != null && question.getRightAnswer() != null) {
                if (response.getResponse().equals(question.getRightAnswer())) {
                    right++;
                }
            }
        }

        return ResponseEntity.ok(right);
    }



    //    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
//        Quiz quiz = quizDao.findById(id).get();
//        List<examQuestions> questionsFromDB = quiz.getQuestions();
//        int right = 0;
//        int i = 0;
//        for (Response response : responses) {
//            if (response.getResponse().equals(questionsFromDB.get(i).getRightAnswer()))
//                right++;
//            i++;
//        }
//        return ResponseEntity.ok(right);
//    }
    public List<Quiz> getAllQuizzes() {
        return quizDao.findAll();
    }

    public Quiz getQuizById(int id) {
        return quizDao.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
    }
}
