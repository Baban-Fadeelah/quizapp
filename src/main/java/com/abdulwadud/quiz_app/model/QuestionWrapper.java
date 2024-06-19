package com.abdulwadud.quiz_app.model;

import lombok.Data;

@Data

public class QuestionWrapper {
    private Integer id;
    private String questionText;
    private String option1;
    private String option2;
    private String option3;

    public QuestionWrapper(Integer id, String questionText, String option1, String option2, String option3) {
        this.id = id;
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
    }
}
