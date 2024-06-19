package com.abdulwadud.quiz_app.dao;

import com.abdulwadud.quiz_app.model.examQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionDao extends JpaRepository<examQuestions, Integer> {

    List<examQuestions> findByExamType(String examType);

    @Query(value = "SELECT * from exam_questions eQ where eQ.exam_type=:examType ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<examQuestions> findRandomQuestionByExamType(String examType, int numQ);
}
