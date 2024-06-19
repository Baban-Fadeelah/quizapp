package com.abdulwadud.quiz_app.service;

import com.abdulwadud.quiz_app.model.examQuestions;
import com.abdulwadud.quiz_app.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public List<examQuestions> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<examQuestions> getQuestionsByExamType(String examType) {
        return questionDao.findByExamType(examType);

    }
    public String addQuestions(examQuestions examQuestions) {
        questionDao.save(examQuestions);
        return "Question added successfully";
    }
}
